package spyusocket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class BaiduBuffer {

	public static void main(String[] args){
		Charset charSet=Charset.forName("GBK");
		InetSocketAddress address=new InetSocketAddress("www.baidu.com",80);
		SocketChannel channel=null;
		try {
			channel=SocketChannel.open(address);
			channel.write(charSet.encode("GET "+"/ HTTP/1.1"+"\r\n\r\n"));
			ByteBuffer buffer=ByteBuffer.allocate(1024);
			while(channel.read(buffer)!=-1){
				buffer.flip();
				System.out.println(charSet.decode(buffer));
				buffer.clear();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(channel!=null){
				try {
					channel.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
		
	}
}
