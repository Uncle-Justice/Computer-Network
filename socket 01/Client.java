import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

//客户端
public class Client {
    public static void main(String []args){
        try{
            //1.创建客户端Socket，指定服务端地址和端口
            Socket socket = new Socket("192.168.1.122", 8888);
            //2.获取输出流，向服务器端发送信息
            OutputStream os = socket.getOutputStream();//字节输出流
            PrintWriter pw = new PrintWriter(os);//将输出流包装为打印流
            pw.write("用户名:admin; 密码:123");
            pw.flush();//刷新缓存，向服务器输出信息
            socket.shutdownOutput();//关闭输出流
            //3.获取输入流，用来读取服务器端的响应信息
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String info = null;
            while((info = br.readLine())!=null){
                System.out.println("我是客户端，服务器说：" + info);
            }

            //4.关闭其他资源
            br.close();
            is.close();
            pw.close();
            os.close();
            socket.close();
        }catch (UnknownHostException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
