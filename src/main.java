
import java.lang.*;
import java.io.BufferedWriter;
import java.io.File;  
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.BufferedReader;

public class main {
	
	
	public static int countline(String pathname)
	{
		//String pathname = "file.c";
		int line = 0;
		File file = new File(pathname);
		try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            
            while ((tempString = reader.readLine()) != null) {          
                line++;
               
            }
            reader.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		return line;
	}
	
	public static int countcharacter(String pathname)
	{
		//String pathname = "file.c";
		int charnum = 0;
		File file = new File(pathname);
		try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            int tempchar;
            
            while ((tempchar = reader.read()) != -1) {          
            	if (((char) tempchar) != '\r'&&((char) tempchar) != '\n') {
                    charnum++;
                }
            }
            //System.out.println("file.c,字符数：" + charnum);
            reader.close();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
		return charnum;
	}
	
	public static int countword(String pathname)
	{
		//String pathname = "file.c";
		int wordnum = 0;
		File file = new File(pathname);
		try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            int tempchar;
           
            boolean isword = false;
            while ((tempchar = reader.read()) != -1) 
            {          
            	if ((((char) tempchar) >='a'&& ((char) tempchar) <='z') || ((((char) tempchar) >= 'A')&&((char) tempchar) <= 'Z')) 
            	{
                    if(isword ==false)
                    {
                    	isword = true;
                    	wordnum++;
                    }
                }
            	else
            	{
            		if(((char) tempchar) ==','||((char) tempchar)==' ')
            		{
            			isword = false;
            		}
            	}
            }
            //System.out.println("file.c,单词数：" + wordnum);
            reader.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		return wordnum;
	}
	
	public static int[] complicatedline(String pathname)
	{
		//String pathname = "file.c";
				int line[] = new int[4];//0是行数，1是代码行，2是注释行，3是空行
				File file = new File(pathname);
				try {
		            BufferedReader reader = new BufferedReader(new FileReader(file));
		            String tempString = null;
		            
		            while ((tempString = reader.readLine()) != null)
		            {          
		                line[0]++;
		                if(tempString =="")
		                {
		                	line[1]++;
		                }
		                else if(tempString.charAt(0)=='/'&&tempString.charAt(1)=='/')
		                {
		                	line[2]++;
		                }
		                else
		                {
		                	line[3]++;
		                }
		            }
		            reader.close();
		            
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
				return line;
	}
	

	
	
	public static void main(String[] args) 
	{
		String strc = "-c"; 
		String strw = "-w";
		String strl = "-l";
		String stro = "-o";
		String outstr = "";
		boolean Needc = false;
		boolean Needw = false;
		boolean Needl = false;
		boolean Needo = false;
		int countnum = 0;//记录要进行几种计算，便于确定参数里面的文件名的位置
		for(String par : args)//遍历参数看进行什么计算
		{
			if(par.equals(strc))
				{Needc = true;countnum++;}
			if(par.equals(strw))
				{Needw = true;countnum++;}
			if(par.equals(strl))
				{Needl = true;countnum++;}
			if(par.equals(stro))
				{Needo = true;}			
		}
		//判断进行什么计算，然后将要输出的结果添加到outstr末尾，便于之后的输出
		if(Needc)
		{
			int charnum = countcharacter(args[countnum]);
			outstr = outstr + "file.c,字符数：" + charnum + " \r\n";
		}
		if(Needw)
		{
			int wordnum = countword(args[countnum]);
			outstr = outstr + "file.c,单词数：" + wordnum + "\r\n";
		}
		if(Needl)
		{
			int linenum = countline(args[countnum]);
			outstr = outstr + "file.c,行数：" + linenum + " \r\n";
		}
		//判断是否制定了输入的文件，没有指定的话输入进result.txt
		if(Needo)
		{
			try{
			File outfile = new File(args[countnum + 2]);
			outfile.createNewFile(); // 创建新文件
	        FileWriter fw = new FileWriter(outfile);  
	        BufferedWriter out = new BufferedWriter(fw);   
	        out.write(outstr);
	        out.flush(); // 把缓存区内容压入文件  
            out.close(); // 最后记得关闭文件  
			}
			catch(Exception e)
			{
				
			}
		}
		else
		{
			try{
				String outpathname = "result.txt";
				File outfile = new File(outpathname);
		        FileWriter fw = new FileWriter(outfile);  
		        BufferedWriter out = new BufferedWriter(fw);   
		        out.write(outstr);
		        //System.out.print("out");		       
                out.flush(); // 把缓存区内容压入文件  
                out.close(); // 最后记得关闭文件  
				}
				catch(Exception e)
				{
					
				}
		}
		
		
		
	}
	
}
