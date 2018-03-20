
import java.lang.*;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.File;  
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.BufferedReader;

public class main {
	
	public static int countline(String pathname)//����������������ȡ�ļ����У���ȡ��һ��+1
	{
		int line = 0;
		File file = new File(pathname);
		try 
		{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String tempString = null;       
            while ((tempString = reader.readLine()) != null) 
            {          
                line++;               
            }
            reader.close();
            
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
		return line;
	}
	
	public static int countcharacter(String pathname)//�����ַ�����������ȡ�ļ����ַ�����ȡ��һ��+1
	{
		int charnum = 0;
		File file = new File(pathname);
		try 
		{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            int tempchar;
            while ((tempchar = reader.read()) != -1)
            {   
            	if((char)tempchar !='\r')//�׺в����У�����18�Ų���԰���������󣬸�����һ�����ж�����
                    charnum++;               
            }
            reader.close();
           
        } 
		catch (Exception e)
        {
            e.printStackTrace();
        }
		return charnum;
	}
	
	public static int countword(String pathname,String stoppathname,int way)//��ȡ�ļ��Ĵ�����֧��ȱ�ʱ���
	{
		int wordnum = 0;
		File file = new File(pathname);
		//file������Ҫͳ�Ƶ��ļ���file2������ͣ�ôʱ�
		ArrayList<String> readword = new ArrayList<String>();
		ArrayList<String> stopword = new ArrayList<String>();
		String addword = "";
		try {
            BufferedReader reader = new BufferedReader(new FileReader(file));         
            int tempchar;
            boolean isword = false;         
            //����ͣ�ôʱ�
            while ((tempchar = reader.read()) != -1) 
            {          
            	if (((char) tempchar) !=','&&((char) tempchar)!=' '&&((char) tempchar)!='\n'&&((char) tempchar)!='\r') //�ǵ���ʱ���1
            	{
            		//(((char) tempchar) >='a'&& ((char) tempchar) <='z') || ((((char) tempchar) >= 'A')&&((char) tempchar) <= 'Z')
                    if(isword ==false)
                    {
                    	isword = true;
                    	wordnum++;
                    }
                    addword = addword + (char) tempchar;
                    
                }
            	else
            	{          		
            			isword = false;
            			if(addword!="")
             			{
             				readword.add(addword);           				
             				addword = "";
             			}            		
            	}
            }
            reader.close();
            if(addword!="")//�����һ������
			{
				readword.add(addword);
				addword = "";
			}
            //��ͣ�ôʱ�
            isword = false;
            addword = "";
            if(way == 2)
            {
            	 File file2 = new File(stoppathname);
            	 BufferedReader reader2 = new BufferedReader(new FileReader(file2));
            	 while ((tempchar = reader2.read()) != -1) 
                 {          
                 	if (((char) tempchar) !=','&&((char) tempchar)!=' '&&((char) tempchar)!='\n'&&((char) tempchar)!='\r')//�ǵ���ʱ���1
                 	{
                         if(isword ==false)
                         {
                         	isword = true;                       	
                         }
                         addword = addword + (char) tempchar;
                     }
                 	else
                 	{                 		
                 			isword = false;
                 			if(addword!="")
                 			{
                 				stopword.add(addword);
                 				addword = "";
                 			}                 			                 	                		
                 	}
                 }
            	 reader2.close();
            	 if(addword!="")//�����һ������
     			{
     				stopword.add(addword);
     				addword = "";
     			}
            	//���жԱ�ͣ�ôʱ�
                 for(String rword : readword)
                 {
                	 for(String sword : stopword)
                	 {
                		 if(rword.equals(sword))
                		 {
                			 wordnum--;
                		 }
                	 }
                 }
                 
            }        
        } catch (Exception e) {
            e.printStackTrace();
        }
		return wordnum;
	}
	
	public static int[] complicatedline(String pathname)
	{
		
			int line[] = new int[4];//0��������1�ǿ��У�2��ע���У�3�Ǵ�����
			File file = new File(pathname);
			boolean isnote = false;//�ж����������ַ�
			boolean islongnote = false;//�жϳ�ע��/*  */
			boolean iscode = true;//�ж��겻�ǿ��к�ע���к󣬶��Ǵ�����
			try {
		        BufferedReader reader = new BufferedReader(new FileReader(file));
		        String tempString = null;	
		        
		        while ((tempString = reader.readLine()) != null)
		        {    
		        	
		        	if(islongnote)//����һ���Ƿ��ڳ�ע����
		        	{
		        		 int i;
		        		 boolean isaddlongnote = true;
		        		 for(i = 0;i<tempString.length();i++)
		        		 {
		        			 if(tempString.charAt(i) == '*'&&i<tempString.length()-1)
		        				 if(tempString.charAt(i+1) =='/')
		        				 {
		        					 islongnote = false;
		        					 if(i != tempString.length()-2)
		        					 {
		        						 isaddlongnote = false;
		        					 }
		        					 break;		        					 
		        				 }
		        		 }
		        		 if(isaddlongnote == true)
		        			 {line[2]++;}
		        		 isaddlongnote = true;
		        		 continue;
		        	}
		        	
		            line[0]++;
		            if(tempString.isEmpty())//��¼����
		            {
		            	line[1]++;
		              	iscode = false;
		            }
		            else 
		            {
		            	boolean checknote = true;
		                for(int i = 0;i<tempString.length();i++)
		                {
		                	//�ж��Ƿ����//ע�͵ĵļ��
		                	if(tempString.charAt(i)!=' '&&tempString.charAt(i)!='/'&&tempString.charAt(i)!='\t'&&i!=0)
		                	{
		                		checknote = false;
		                			                		
		                	}
		                	if(checknote)
		                	{
		                		if(!islongnote&&tempString.charAt(i)=='/'&&i<tempString.length()-1)//�ж�/*
		                			if(tempString.charAt(i+1)=='*')
		                			{
		                				line[2]++;
		                				islongnote = true;
		                				isnote = false;	
		                				iscode = false;	
		                				checknote = false;
		                			}	         
			                	if(!islongnote&&!isnote&&tempString.charAt(i)=='/'&&i<tempString.length()-1)//�ж�//
			                		if(tempString.charAt(i+1)=='/')
			                		{
			                			line[2]++;
		                				iscode = false;
		                				checknote = false;
			                		}
			                	
		                	}
		                	else
		                	{
		                		if(tempString.charAt(i)=='/'&&i<tempString.length()-1)//�ж�/*
		                			if(tempString.charAt(i+1)=='*')
		                			{
		                				islongnote = true;
		                			}	     
		                		if(islongnote)		                //�ж�*/
		                			if(tempString.charAt(i)=='*'&&i<tempString.length()-1)
			                			if(tempString.charAt(i+1)=='/'&&i<tempString.length()-1)
			                			{
			                				islongnote = false;
			                			}			                					                		
		                	}
		                	
		                }
		                	
		            }
		            if(iscode)//��¼������
		            {
		                line[3]++;
		            }
		            else
		            {
		                iscode = true;
		            }
		                
		        }
		        reader.close();
		            
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
			return line;
	}
	
	public static void outputresult(int way,String outstr,String Infilename)
	{
		if(way==1)
		{
			try{
				File outfile = new File(Infilename);
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
		else if(way==2)
		{
			try{
				String outpathname = "result.txt";
				File outfile = new File(outpathname);
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
	}
	 private static ArrayList<String> getFileList(File dir, List<File> list,int deep) {  
	        File[] files = dir.listFiles();
	        ArrayList<String> filepath = new ArrayList<String>();
	        for(File file:files)  
	        {  
	            if(file.isDirectory())  
	            {  
	                getFileList(file, list, deep + 1);  
	            }  
	            else  
	            {   	                
	                list.add(file);  	                 
	            }  
	        }  
	        if(deep == 0)
	        {
	        	for(File tempfile  : files)
	        	{ 
	        		filepath.add(tempfile.getPath());
	        	}
	        	return filepath;
	        	
	        }
	        return null;
	    }  
	
	
	public static void main(String[] args) 
	{
		String strc = "-c"; 
		String strw = "-w";
		String strl = "-l";
		String stro = "-o";
		String stra = "-a";
		String strs = "-s";
		String stre = "-e";
		String outstr = "";
		boolean Needc = false;
		boolean Needw = false;
		boolean Needl = false;
		boolean Needo = false;
		boolean Needa = false;
		boolean Needs = false;
		boolean Neede = false;//�⼸��Need�������жϣ��Ƿ��õ�-*�Ĺ���
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
			if(par.equals(stra))
				{Needa = true;countnum++;}
			if(par.equals(strs))
				{Needs = true;countnum++;}
			if(par.equals(stre))
				{Neede = true;}
		}
		if(!Needs)//�������Ҫ�������������ļ��У���ֻ��Ҫ����file.c�ļ��Ľ��������Ҫ�Ȳ������ļ��е��ļ���
		{
			//�жϽ���ʲô���㣬Ȼ��Ҫ����Ľ����ӵ�outstrĩβ������֮������
			if(Needc)
			{
				int charnum = countcharacter(args[countnum]);
				outstr = outstr + args[countnum] + ",�ַ�����" + charnum + "\r\n";
			}
			if(Needw)
			{
				if(!Neede)
				{
					int wordnum = countword(args[countnum],args[countnum],1);
					outstr = outstr + args[countnum] + ",��������" + wordnum + "\r\n";
				}
				else
				{
					int wordnum;
					if(Needo)
						wordnum = countword(args[countnum],args[args.length-3],2);
					else
						wordnum = countword(args[countnum],args[args.length-1],2);
					outstr = outstr + args[countnum] + ",��������" + wordnum + "\r\n";
				}
				
			}
			if(Needl)
			{
				int linenum = countline(args[countnum]);
				outstr = outstr + args[countnum] + ",������" + linenum + "\r\n";
			}
			if(Needa)
			{
				int clinenum[] = new int [4];
				clinenum = complicatedline(args[countnum]);
				outstr = outstr + args[countnum] + ", ������/����/ע���У� " + clinenum[3] + "/" + clinenum[1] + "/" + clinenum[2] + "\r\n";
			}
			//�ж��Ƿ��ƶ���������ļ���û��ָ���Ļ������result.txt
			if(Needo)
			{
					outputresult(1,outstr,args[args.length-1]);
			}
			else
			{
				outputresult(2,outstr,strc);
			}
		}
		else
		{//��ȡĿ¼�����з���Ҫ���ļ���Ȼ����м��
			if(args[countnum].charAt(0)=='*')
			{
				//File file = new File("./");   
		        // ��ø��ļ����ڵ������ļ�   
		        //File[] array = file.listFiles(); 
				 File dir = new File("./");  
	            List<File> list = new ArrayList<File>(); 
		        getFileList(dir,list,0);
		        for(File array  : list)
		        {   
		        	
		            if(array.getName().endsWith(args[countnum].substring(1)))//������ļ�������׺���Ƿ����
		            {   
		            	if(Needc)//���μ���Ƿ���м����ַ��������ʡ������Ȳ���
						{
							int charnum = countcharacter(array.getPath());
							outstr = outstr + array.getName() + ",�ַ�����" + charnum + "\r\n";
						}
						if(Needw)
						{
							if(!Neede)
							{
								int wordnum = countword(array.getPath(),array.getPath(),1);
								outstr = outstr + array.getName() + ",��������" + wordnum + "\r\n";
							}
							else
							{
								int wordnum;
								if(Needo)
									wordnum = countword(array.getPath(),args[args.length-3],2);
								else
									wordnum = countword(array.getPath(),args[args.length-1],2);
								outstr = outstr + array.getName() + ",��������" + wordnum + "\r\n";
							}
						}
						if(Needl)
						{
							int linenum = countline(array.getPath());
							outstr = outstr + array.getName() + ",������" + linenum + "\r\n";
						}
						if(Needa)
						{
							int clinenum[] = new int [4];
							clinenum = complicatedline(array.getPath());
							outstr = outstr + array.getName() + ", ������/����/ע���У� " + clinenum[3] + "/" + clinenum[1] + "/" + clinenum[2] + "\r\n";
						}
		            }	            
		        }
		        if(Needo)
				{
					outputresult(1,outstr,args[args.length-1]);
				}
				else
				{
					outputresult(2,outstr,strc);
				}
			}
			else
			{//�׺в����и��������󣬼��ϲ���԰�в���Ҳ��18�ź���������󣬲��ò��ֽ����޸ģ�����д����ȫ·���µĴ���ʽ
				int pointposition = -1;
				for(int i=args[countnum].length()-1;i>=0;i--)
				{
					if(args[countnum].charAt(i)=='.')
						if(args[countnum].charAt(i-1)=='*')
							pointposition = i;
				}
				if(pointposition == -1)
				{
					System.out.println("·���������");
				}
		        // ��ø��ļ����ڵ������ļ�   
				File dir = new File(args[countnum].substring(0,pointposition-1));  
	            List<File> list = new ArrayList<File>(); 
		        getFileList(dir,list,0);
		        //File[] array = file.listFiles();   
		        for(File array  : list)
		        {   
		            if(array.getPath().endsWith(args[countnum].substring(pointposition)))//������ļ�������׺���Ƿ����
		            {   
		            	if(Needc)//���μ���Ƿ���м����ַ��������ʡ������Ȳ���
						{
							int charnum = countcharacter(array.getPath());
							outstr = outstr + array.getName()+ ",�ַ�����" + charnum + "\r\n";
						}
						if(Needw)
						{
							if(!Neede)
							{
								int wordnum = countword(array.getPath(), array.getPath(),1);
								outstr = outstr + array.getName() + ",��������" + wordnum + "\r\n";
							}
							else
							{
								int wordnum;
								if(Needo)
									wordnum = countword(array.getPath(),args[countnum].substring(0,pointposition-1) + args[args.length-3],2);
								else
									wordnum = countword(array.getPath(),args[countnum].substring(0,pointposition-1) + args[args.length-1],2);
								outstr = outstr + array.getName() + ",��������" + wordnum + "\r\n";
							}
						}
						if(Needl)
						{
							int linenum = countline(array.getPath());
							outstr = outstr + array.getName() + ",������" + linenum + "\r\n";
						}
						if(Needa)
						{
							int clinenum[] = new int [4];
							clinenum = complicatedline(array.getPath());
							outstr = outstr + array.getName() + ", ������/����/ע���У� " + clinenum[3] + "/" + clinenum[1] + "/" + clinenum[2] + "\r\n";
						}
		            }	            
		        }
		        if(Needo)
				{
					outputresult(1,outstr,args[args.length-1]);
				}
				else
				{
					outputresult(2,outstr,strc);
				}
			
			}
			
	       
		} 		
	}
	
}
