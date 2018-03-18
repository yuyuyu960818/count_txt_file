import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class testmain {
//进行下面10个测试，当测试成功时输出pass + 测试序号 + test，失败时输出fail + 测试序号 + test
	public static void main(String args[])
	{
		//验证-c功能，返回字符数
		String r1[] = {"file.c,字符数：82 "};
		String para1[] ={ "-c","file.c"};
		main.main(para1);
		System.out.println("test1 is " + test("result.txt",r1));
		//验证-w功能，返回单词数
		String r2[] = {"file.c,单词数：13"};
		String para2[] ={ "-w","file.c"};
		main.main(para2);
		System.out.println("test2 is " + test("result.txt",r2));
		//验证-l功能，返回行数
		String r3[] = {"file.c,行数：9 "};
		String para3[] ={ "-l","file.c"};
		main.main(para3);
		System.out.println("test3 is " + test("result.txt",r3));
		//验证-a功能，返回复杂的行数据
		String r4[] = {"file.c, 代码行/空行/注释行： 6/1/2 "};
		String para4[] ={ "-a","file.c"};
		main.main(para4);
		System.out.println("test4 is " + test("result.txt",r4));
		//对比，可看出复杂行数是否正确
		String r5[] = {"file.c,行数：9 ", "file.c, 代码行/空行/注释行： 6/1/2 "};
		String para5[] ={ "-l","-a","file.c"};
		main.main(para5);
		System.out.println("test5 is " + test("result.txt",r5));
		//验证-o功能，输出数据放在output.txt中
		String r6[] = {"file.c,行数：9 ", "file.c, 代码行/空行/注释行： 6/1/2 "};
		String para6[] ={ "-l","-a","file.c","-o","output.txt"};
		main.main(para6);
		System.out.println("test6 is " + test("output.txt",r6));
		//验证-e功能，实现停止单词表
		String r7[] = {"file.c,单词数：7"};
		String para7[] ={ "-w","file.c","-e","stop.txt"};
		main.main(para7);
		System.out.println("test7 is " + test("result.txt",r7));
		//验证除-s所有功能一起使用时，程序的正确性，输出与-*的位置无关
		String r8[] = {"file.c,字符数：82 ",
				"file.c,单词数：7",
				"file.c,行数：9 ",
				"file.c, 代码行/空行/注释行： 6/1/2 "	};
		String para8[] ={ "-c","-l","-w","-a","file.c","-e","stop.txt","-o","output.txt"};
		main.main(para8);
		System.out.println("test8 is " + test("output.txt",r8));
		//验证-s功能
		String r9[] = {"file.c,行数：9 ","file2.c,行数：2 "};
		String para9[] ={ "-s","-l","*.c","-o","output.txt"};
		main.main(para9);
		System.out.println("test9 is " + test("output.txt",r9));
		//验证所有的功能一起使用
		String r10[] = {"file.c,字符数：82 ",
				"file.c,单词数：7",
				"file.c,行数：9 ",
				"file.c, 代码行/空行/注释行： 6/1/2 ",
				"file2.c,字符数：10 ",
				"file2.c,单词数：2",
				"file2.c,行数：2 ",
				"file2.c, 代码行/空行/注释行： 2/0/0 "
	};
		String para10[] ={ "-c","-l","-w","-a","-s","*.c","-e","stop.txt","-o","output.txt"};
		main.main(para10);
		System.out.println("test10 is " + test("output.txt",r10));
		//单元测试结束，该单元测试基本覆盖了所有的可执行语句
	}
	public static boolean test(String result,String right_result[])
	{
		try
		{
			File file = new File(result);
			BufferedReader reader = new BufferedReader(new FileReader(file)); 
			String tempString = null;	
			int i = 0;//读取到测试正确结果的第几行
			while((tempString = reader.readLine()) != null)
			{
				if(!tempString.equals(right_result[i]))
					return false;
				i++;
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return true;
	}

}
