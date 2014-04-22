package spyusocket.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioExample {

	private ByteBuffer buffer=null;
	
	public static void main(String[] args){
		
		String inFile="d:\\WINDOWS.GHO";
		String outFile="c:\\out.txt";
		
		try {
			
			FileChannel inFileChannel=new FileInputStream(new File(inFile)).getChannel();
			
			FileChannel outFileChannel=new FileOutputStream(new File(outFile)).getChannel();
			
			ByteBuffer buffer=ByteBuffer.allocate(1024);
			System.out.println(buffer.limit());
			long curTime=System.currentTimeMillis();
			while(true){
				buffer.clear();
				
				int r=inFileChannel.read(buffer);
				if(r==-1){
					break;
				}
				buffer.flip();				
				outFileChannel.write(buffer);
//				buffer.compact();
			}
			System.out.println(System.currentTimeMillis()-curTime);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
