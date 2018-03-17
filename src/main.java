
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
            //System.out.println("file.c,�ַ�����" + charnum);
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
            //System.out.println("file.c,��������" + wordnum);
            reader.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		return wordnum;
	}
	
	public static int[] complicatedline(String pathname)
	{
		//String pathname = "file.c";
				int line[] = new int[4];//0��������1�Ǵ����У�2��ע���У�3�ǿ���
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
		int countnum = 0;//��¼Ҫ���м��ּ��㣬����ȷ������������ļ�����λ��
		for(String par : args)//��������������ʲô����
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
		//�жϽ���ʲô���㣬Ȼ��Ҫ����Ľ����ӵ�outstrĩβ������֮������
		if(Needc)
		{
			int charnum = countcharacter(args[countnum]);
			outstr = outstr + "file.c,�ַ�����" + charnum + " \r\n";
		}
		if(Needw)
		{
			int wordnum = countword(args[countnum]);
			outstr = outstr + "file.c,��������" + wordnum + "\r\n";
		}
		if(Needl)
		{
			int linenum = countline(args[countnum]);
			outstr = outstr + "file.c,������" + linenum + " \r\n";
		}
		//�ж��Ƿ��ƶ���������ļ���û��ָ���Ļ������result.txt
		if(Needo)
		{
			try{
			File outfile = new File(args[countnum + 2]);
			outfile.createNewFile(); // �������ļ�
	        FileWriter fw = new FileWriter(outfile);  
	        BufferedWriter out = new BufferedWriter(fw);   
	        out.write(outstr);
	        out.flush(); // �ѻ���������ѹ���ļ�  
            out.close(); // ���ǵùر��ļ�  
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
                out.flush(); // �ѻ���������ѹ���ļ�  
                out.close(); // ���ǵùر��ļ�  
				}
				catch(Exception e)
				{
					
				}
		}
		
		
		
	}
	
}
