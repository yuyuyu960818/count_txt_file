import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class testmain {
//��������8�����ԣ������Գɹ�ʱ���pass + ������� + test��ʧ��ʱ���fail + ������� + test
	public static void main(String args[])
	{
		//��֤-c���ܣ������ַ���
		String r1[] = {"file.c,�ַ�����91"};
		String para1[] ={ "-c","file.c"};
		main.main(para1);
		System.out.println("test1 is " + test("result.txt",r1));
		//��֤-w���ܣ����ص�����
		String r2[] = {"file.c,��������18"};
		String para2[] ={ "-w","file.c"};
		main.main(para2);
		System.out.println("test2 is " + test("result.txt",r2));
		//��֤-l���ܣ���������
		String r3[] = {"file.c,������9"};
		String para3[] ={ "-l","file.c"};
		main.main(para3);
		System.out.println("test3 is " + test("result.txt",r3));
		//��֤-a���ܣ����ظ��ӵ�������
		String r4[] = {"file.c, ������/����/ע���У� 6/1/2"};
		String para4[] ={ "-a","file.c"};
		main.main(para4);
		System.out.println("test4 is " + test("result.txt",r4));
		//�Աȣ��ɿ������������Ƿ���ȷ
		String r5[] = {"file.c,������9", "file.c, ������/����/ע���У� 6/1/2"};
		String para5[] ={ "-l","-a","file.c"};
		main.main(para5);
		System.out.println("test5 is " + test("result.txt",r5));
		//��֤-o���ܣ�������ݷ���output.txt��
		String r6[] = {"file.c,������9", "file.c, ������/����/ע���У� 6/1/2"};
		String para6[] ={ "-l","-a","file.c","-o","output.txt"};
		main.main(para6);
		System.out.println("test6 is " + test("output.txt",r6));
		//��֤-e���ܣ�ʵ��ֹͣ���ʱ�
		String r7[] = {"file.c,��������17"};
		String para7[] ={ "-w","file.c","-e","stop.txt"};
		main.main(para7);
		System.out.println("test7 is " + test("result.txt",r7));
		//��֤��-s���й���һ��ʹ��ʱ���������ȷ�ԣ������-*��λ���޹�
		String r8[] = {"file.c,�ַ�����91",
				"file.c,��������17",
				"file.c,������9",
				"file.c, ������/����/ע���У� 6/1/2"	};
		String para8[] ={ "-c","-l","-w","-a","file.c","-e","stop.txt","-o","output.txt"};
		main.main(para8);
		System.out.println("test8 is " + test("output.txt",r8));
		//��֤-s����
		String r9[] = {"file.c,������9","file2.c,������2"};
		String para9[] ={ "-s","-l","*.c","-o","output.txt"};
		main.main(para9);
		System.out.println("test9 is " + test("output.txt",r9));
		//��֤���еĹ���һ��ʹ��
		String r10[] = {"file.c,�ַ�����91",
				"file.c,��������17",
				"file.c,������9",
				"file.c, ������/����/ע���У� 6/1/2",
				"file2.c,�ַ�����12",
				"file2.c,��������2",
				"file2.c,������2",
				"file2.c, ������/����/ע���У� 2/0/0"
	};
		String para10[] ={ "-c","-l","-w","-a","-s","*.c","-e","stop.txt","-o","output.txt"};
		main.main(para10);
		System.out.println("test10 is " + test("output.txt",r10));
		//��Ԫ���Խ������õ�Ԫ���Ի������������еĿ�ִ�����
	}
	public static boolean test(String result,String right_result[])
	{
		try
		{
			File file = new File(result);
			BufferedReader reader = new BufferedReader(new FileReader(file)); 
			String tempString = null;	
			int i = 0;//��ȡ��������ȷ����ĵڼ���
			while((tempString = reader.readLine()) != null)
			{
				if(!tempString.equals(right_result[i]))
					return false;;
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
