
import java.lang.*;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;  
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.BufferedReader;

public class main {
	
	
	public static int countline(String pathname)
	{
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
            reader.close();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
		return charnum;
	}
	
	public static int countword(String pathname,String stoppathname,int way)
	{
		int wordnum = 0;
		File file = new File(pathname);
		//file用来打开要统计的文件，file2用来打开停用词表
		ArrayList<String> readword = new ArrayList<String>();
		ArrayList<String> stopword = new ArrayList<String>();
		String addword = "";
		try {
            BufferedReader reader = new BufferedReader(new FileReader(file));         
            int tempchar;
            boolean isword = false;         
            //读非停用词表
            while ((tempchar = reader.read()) != -1) 
            {          
            	if ((((char) tempchar) >='a'&& ((char) tempchar) <='z') || ((((char) tempchar) >= 'A')&&((char) tempchar) <= 'Z')) 
            	{
                    if(isword ==false)
                    {
                    	isword = true;
                    	wordnum++;
                    }
                    addword = addword + (char) tempchar;
                }
            	else
            	{
            		if(((char) tempchar) ==','||((char) tempchar)==' '||((char) tempchar)=='\n'||((char) tempchar)=='\r')
            		{
            			isword = false;
            			if(addword!="")
             			{
             				readword.add(addword);
             				addword = "";
             			}
            		}
            	}
            }
            reader.close();
            if(addword!="")//读最后一个单词
			{
				readword.add(addword);
				addword = "";
			}
            //读停用词表
            isword = false;
            addword = "";
            if(way == 2)
            {
            	
            	//System.out.println(stoppathname);
            	 File file2 = new File(stoppathname);
            	 BufferedReader reader2 = new BufferedReader(new FileReader(file2));
            	 while ((tempchar = reader2.read()) != -1) 
                 {          
                 	if ((((char) tempchar) >='a'&& ((char) tempchar) <='z') || ((((char) tempchar) >= 'A')&&((char) tempchar) <= 'Z')) 
                 	{
                         if(isword ==false)
                         {
                         	isword = true;
                         	
                         }
                         addword = addword + (char) tempchar;
                     }
                 	else
                 	{
                 		if(((char) tempchar) ==','||((char) tempchar)==' '||((char) tempchar)=='\n'||((char) tempchar)=='\r')
                 		{
                 			isword = false;
                 			if(addword!="")
                 			{
                 				stopword.add(addword);
                 				addword = "";
                 			}
                 			
                 			
                 		}
                 	}
                 }
            	 reader2.close();
            	 if(addword!="")//读最后一个单词
     			{
     				stopword.add(addword);
     				addword = "";
     			}
            	//进行对比停用词表
                 for(String rword : readword)
                 {
                	 for(String sword : stopword)
                	 {
                		 //System.out.println(sword);
                		 if(rword.equals(sword))
                		 {
                			 wordnum--;
                			 System.out.println(rword);
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
		
			int line[] = new int[4];//0是行数，1是空行，2是注释行，3是代码行
			File file = new File(pathname);
			boolean isnote = false;//判断连续两个字符
			boolean islongnote = false;//判断长注释/*  */
			boolean iscode = true;//判断完不是空行和注释行后，都是代码行
			try {
		        BufferedReader reader = new BufferedReader(new FileReader(file));
		        String tempString = null;		            
		        while ((tempString = reader.readLine()) != null)
		        {    
		        	if(islongnote)
		        	{
		        		 for(int i = 0;i<tempString.length();i++)
		        		 {
		        			 if(tempString.charAt(i) == '*'&&i<tempString.length()-1)
		        				 if(tempString.charAt(i+1) =='/')
		        				 {
		        					 islongnote = false;
		        					 break;
		        				 }
		        		 }
		        		 //System.out.println("noteline++1");
		        		 line[2]++;
		        		 continue;
		        	}
		        	
		            line[0]++;
		            if(tempString.isEmpty())
		            {
		            	line[1]++;
		              	iscode = false;
		              	//System.out.println("empytline++");
		            }
		            else 
		            {
		            	boolean checknote = true;
		                for(int i = 0;i<tempString.length();i++)
		                {
		                	//判断是否进行//注释的的检测
		                	if(tempString.charAt(i)!=' '&&tempString.charAt(i)!='/'&&tempString.charAt(i)!='\t')
		                	{
		                		checknote = false;
		                			                		
		                	}
		                	if(checknote)
		                	{
		                		if(!islongnote&&tempString.charAt(i)=='/'&&i<tempString.length()-1)
		                			if(tempString.charAt(i+1)=='*')
		                			{
		                				//System.out.println("noteline++2");
		                				line[2]++;
		                				islongnote = true;
		                				isnote = false;	
		                				iscode = false;	
		                				checknote = false;
		                			}	         
			                	if(!islongnote&&!isnote&&tempString.charAt(i)=='/'&&i<tempString.length()-1)
			                		if(tempString.charAt(i+1)=='/')
			                		{
			                			line[2]++;
		                				//System.out.println("noteline++3");
		                				iscode = false;
		                				checknote = false;
			                		}
			                	
		                	}
		                	else
		                	{
		                		if(tempString.charAt(i)=='/'&&i<tempString.length()-1)
		                			if(tempString.charAt(i+1)=='*')
		                			{
		                				islongnote = true;
		                			}	     
		                		if(islongnote)		                
		                			if(tempString.charAt(i)=='*'&&i<tempString.length()-1)
			                			if(tempString.charAt(i+1)=='/'&&i<tempString.length()-1)
			                			{
			                				islongnote = false;
			                			}			                					                		
		                	}
		                	
		                }
		                	
		            }
		            if(iscode)
		            {
		                line[3]++;
		                //System.out.println("codeline++");
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
		else if(way==2)
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
		boolean Neede = false;
		int countnum = 0;//记录要进行几种计算，便于确定参数里面的文件名的位置
		for(String par : args)//遍历参数看进行什么计算
		{
			System.out.println(par);
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
		if(!Needs)//如果不需要进行搜索整个文件夹，则只需要进行file.c文件的结果，否则要先查整个文件夹的文件。
		{
			//判断进行什么计算，然后将要输出的结果添加到outstr末尾，便于之后的输出
			if(Needc)
			{
				int charnum = countcharacter(args[countnum]);
				outstr = outstr + args[countnum] + ",字符数：" + charnum + " \r\n";
			}
			if(Needw)
			{
				if(!Neede)
				{
					int wordnum = countword(args[countnum],args[countnum],1);
					outstr = outstr + args[countnum] + ",单词数：" + wordnum + "\r\n";
				}
				else
				{
					int wordnum;
					if(Needo)
						wordnum = countword(args[countnum],args[args.length-3],2);
					else
						wordnum = countword(args[countnum],args[args.length-1],2);
					outstr = outstr + args[countnum] + ",单词数：" + wordnum + "\r\n";
				}
				
			}
			if(Needl)
			{
				int linenum = countline(args[countnum]);
				outstr = outstr + args[countnum] + ",行数：" + linenum + " \r\n";
			}
			if(Needa)
			{
				int clinenum[] = new int [4];
				clinenum = complicatedline(args[countnum]);
				outstr = outstr + args[countnum] + ", 代码行/空行/注释行： " + clinenum[3] + "/" + clinenum[1] + "/" + clinenum[2] + " \r\n";
			}
			//判断是否制定了输入的文件，没有指定的话输入进result.txt
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
		{
			System.out.println("run to here");
			int suffixplace = 0;
			if(Needo)
				suffixplace++;
			if(Neede)
				suffixplace++;
			//System.out.println(suffix);
			for(;countnum<args.length-suffixplace-suffixplace;countnum++)
			{
				if(Needc)
				{
					int charnum = countcharacter(args[countnum]);
					outstr = outstr + args[countnum]+ ",字符数：" + charnum + " \r\n";
				}
				if(Needw)
				{
					if(!Neede)
					{
						int wordnum = countword(args[countnum],args[countnum],1);
						outstr = outstr + args[countnum] + ",单词数：" + wordnum + "\r\n";
					}
					else
					{
						int wordnum;
						if(Needo)
							wordnum = countword(args[countnum],args[args.length-3],2);
						else
							wordnum = countword(args[countnum],args[args.length-1],2);
						outstr = outstr + args[countnum] + ",单词数：" + wordnum + "\r\n";
					}
				}
				if(Needl)
				{
					int linenum = countline(args[countnum]);
					outstr = outstr + args[countnum] + ",行数：" + linenum + " \r\n";
				}
				if(Needa)
				{
					int clinenum[] = new int [4];
					clinenum = complicatedline(args[countnum]);
					outstr = outstr + args[countnum] + ", 代码行/空行/注释行： " + clinenum[3] + "/" + clinenum[1] + "/" + clinenum[2] + " \r\n";
				}
			}
			
			/*for (int i = 0; i < tempList.length; i++)
			{
				if (tempList[i].isFile()) 
				{				
					//读取某个文件夹下的所有文件,然后选出.c结尾的文件
					if(tempList[i].getName().endsWith(suffix))
					{
						System.out.println("文件："+tempList[i].getName());
						if(Needc)
						{
							int charnum = countcharacter(tempList[i].getName());
							outstr = outstr + tempList[i].getName() + ",字符数：" + charnum + " \r\n";
						}
						if(Needw)
						{
							int wordnum = countword(tempList[i].getName());
							outstr = outstr + tempList[i].getName() + ",单词数：" + wordnum + "\r\n";
						}
						if(Needl)
						{
							int linenum = countline(tempList[i].getName());
							outstr = outstr + tempList[i].getName() + ",行数：" + linenum + " \r\n";
						}
						if(Needa)
						{
							int clinenum[] = new int [4];
							clinenum = complicatedline(tempList[i].getName());
							outstr = outstr + tempList[i].getName() + ", 代码行/空行/注释行： " + clinenum[3] + "/" + clinenum[1] + "/" + clinenum[2] + " \r\n";
						}
						//判断是否制定了输入的文件，没有指定的话输入进result.txt
						
					}
				}

			}*/
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
