import java.io.IOException;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException;


public class Main {
	
    public static final int MCP23017_ADDRESS = 0x20;

    private static final int IODIRA_REGISTER = 0x00; //IODIRA Register. Responsible for input or output
    private static final int IODIRB_REGISTER = 0x01; //IODIRB Register. Responsible for input or output
    
    private static final int GPIOA_REGISTER = 0x12; //GPIOA Register. Write or read value
    private static final int GPIOB_REGISTER = 0x13; //GPIOB Register. Write or read value
    
    //private static final int GPPUA_REGISTER = 0x0C; //PORT A Pull-up value. If set configures the internal pull-ups
    private static final int GPPUB_REGISTER = 0x0D; ///PORT B Pull-up value. If set configures the internal pull-ups
    
    public static void main(String args[]) throws InterruptedException, UnsupportedBusNumberException, IOException {
        System.out.println("MCP23017 Example");
        I2CBus i2c = I2CFactory.getInstance(I2CBus.BUS_1);
        I2CDevice device = i2c.getDevice(MCP23017_ADDRESS);
        

        device.write(IODIRA_REGISTER, (byte) 0x00);
        
        device.write(IODIRB_REGISTER, (byte) 0xFF);
        device.write(GPPUB_REGISTER, (byte) 0xFF);
        
        while(true){

            System.out.println(device.read(GPIOB_REGISTER));
        	Thread.sleep(2000);
            device.write(GPIOA_REGISTER, (byte) 0x00);
        	Thread.sleep(2000);
            device.write(GPIOA_REGISTER, (byte) 0xFF);
        }
        
    }
}

