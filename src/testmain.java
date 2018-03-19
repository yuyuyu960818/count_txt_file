import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class testmain {
//è¿›è¡Œä¸‹é¢10ä¸ªæµ‹è¯•ï¼Œå½“æµ‹è¯•æˆåŠŸæ—¶è¾“å‡ºpass + æµ‹è¯•åºå· + testï¼Œå¤±è´¥æ—¶è¾“å‡ºfail + æµ‹è¯•åºå· + test
	public static void main(String args[])
	{
<<<<<<< HEAD
		//éªŒè¯-cåŠŸèƒ½ï¼Œè¿”å›å­—ç¬¦æ•°
		String r1[] = {"file.c,å­—ç¬¦æ•°ï¼š82 "};
		String para1[] ={ "-c","file.c"};
		main.main(para1);
		System.out.println("test1 is " + test("result.txt",r1));
		//éªŒè¯-wåŠŸèƒ½ï¼Œè¿”å›å•è¯æ•°
		String r2[] = {"file.c,å•è¯æ•°ï¼š13"};
		String para2[] ={ "-w","file.c"};
		main.main(para2);
		System.out.println("test2 is " + test("result.txt",r2));
		//éªŒè¯-låŠŸèƒ½ï¼Œè¿”å›è¡Œæ•°
		String r3[] = {"file.c,è¡Œæ•°ï¼š9 "};
		String para3[] ={ "-l","file.c"};
		main.main(para3);
		System.out.println("test3 is " + test("result.txt",r3));
		//éªŒè¯-aåŠŸèƒ½ï¼Œè¿”å›å¤æ‚çš„è¡Œæ•°æ®
		String r4[] = {"file.c, ä»£ç è¡Œ/ç©ºè¡Œ/æ³¨é‡Šè¡Œï¼š 6/1/2 "};
		String para4[] ={ "-a","file.c"};
		main.main(para4);
		System.out.println("test4 is " + test("result.txt",r4));
		//å¯¹æ¯”ï¼Œå¯çœ‹å‡ºå¤æ‚è¡Œæ•°æ˜¯å¦æ­£ç¡®
		String r5[] = {"file.c,è¡Œæ•°ï¼š9 ", "file.c, ä»£ç è¡Œ/ç©ºè¡Œ/æ³¨é‡Šè¡Œï¼š 6/1/2 "};
		String para5[] ={ "-l","-a","file.c"};
		main.main(para5);
		System.out.println("test5 is " + test("result.txt",r5));
		//éªŒè¯-oåŠŸèƒ½ï¼Œè¾“å‡ºæ•°æ®æ”¾åœ¨output.txtä¸­
		String r6[] = {"file.c,è¡Œæ•°ï¼š9 ", "file.c, ä»£ç è¡Œ/ç©ºè¡Œ/æ³¨é‡Šè¡Œï¼š 6/1/2 "};
		String para6[] ={ "-l","-a","file.c","-o","output.txt"};
		main.main(para6);
		System.out.println("test6 is " + test("output.txt",r6));
		//éªŒè¯-eåŠŸèƒ½ï¼Œå®ç°åœæ­¢å•è¯è¡¨
		String r7[] = {"file.c,å•è¯æ•°ï¼š7"};
		String para7[] ={ "-w","file.c","-e","stop.txt"};
		main.main(para7);
		System.out.println("test7 is " + test("result.txt",r7));
		//éªŒè¯é™¤-sæ‰€æœ‰åŠŸèƒ½ä¸€èµ·ä½¿ç”¨æ—¶ï¼Œç¨‹åºçš„æ­£ç¡®æ€§ï¼Œè¾“å‡ºä¸-*çš„ä½ç½®æ— å…³
		String r8[] = {"file.c,å­—ç¬¦æ•°ï¼š82 ",
				"file.c,å•è¯æ•°ï¼š7",
				"file.c,è¡Œæ•°ï¼š9 ",
				"file.c, ä»£ç è¡Œ/ç©ºè¡Œ/æ³¨é‡Šè¡Œï¼š 6/1/2 "	};
		String para8[] ={ "-c","-l","-w","-a","file.c","-e","stop.txt","-o","output.txt"};
		main.main(para8);
		System.out.println("test8 is " + test("output.txt",r8));
		//éªŒè¯-såŠŸèƒ½
		String r9[] = {"file.c,è¡Œæ•°ï¼š9 ","file2.c,è¡Œæ•°ï¼š2 "};
		String para9[] ={ "-s","-l","*.c","-o","output.txt"};
		main.main(para9);
		System.out.println("test9 is " + test("output.txt",r9));
		//éªŒè¯æ‰€æœ‰çš„åŠŸèƒ½ä¸€èµ·ä½¿ç”¨
		String r10[] = {"file.c,å­—ç¬¦æ•°ï¼š82 ",
				"file.c,å•è¯æ•°ï¼š7",
				"file.c,è¡Œæ•°ï¼š9 ",
				"file.c, ä»£ç è¡Œ/ç©ºè¡Œ/æ³¨é‡Šè¡Œï¼š 6/1/2 ",
				"file2.c,å­—ç¬¦æ•°ï¼š10 ",
				"file2.c,å•è¯æ•°ï¼š2",
				"file2.c,è¡Œæ•°ï¼š2 ",
				"file2.c, ä»£ç è¡Œ/ç©ºè¡Œ/æ³¨é‡Šè¡Œï¼š 2/0/0 "
=======
		//ÑéÖ¤-c¹¦ÄÜ£¬·µ»Ø×Ö·ûÊı
		String r1[] = {"file.c,×Ö·ûÊı£º91"};
		String para1[] ={ "-c","file.c"};
		main.main(para1);
		System.out.println("test1 is " + test("result.txt",r1));
		//ÑéÖ¤-w¹¦ÄÜ£¬·µ»Øµ¥´ÊÊı
		String r2[] = {"file.c,µ¥´ÊÊı£º18"};
		String para2[] ={ "-w","file.c"};
		main.main(para2);
		System.out.println("test2 is " + test("result.txt",r2));
		//ÑéÖ¤-l¹¦ÄÜ£¬·µ»ØĞĞÊı
		String r3[] = {"file.c,ĞĞÊı£º9"};
		String para3[] ={ "-l","file.c"};
		main.main(para3);
		System.out.println("test3 is " + test("result.txt",r3));
		//ÑéÖ¤-a¹¦ÄÜ£¬·µ»Ø¸´ÔÓµÄĞĞÊı¾İ
		String r4[] = {"file.c, ´úÂëĞĞ/¿ÕĞĞ/×¢ÊÍĞĞ£º 6/1/2"};
		String para4[] ={ "-a","file.c"};
		main.main(para4);
		System.out.println("test4 is " + test("result.txt",r4));
		//¶Ô±È£¬¿É¿´³ö¸´ÔÓĞĞÊıÊÇ·ñÕıÈ·
		String r5[] = {"file.c,ĞĞÊı£º9", "file.c, ´úÂëĞĞ/¿ÕĞĞ/×¢ÊÍĞĞ£º 6/1/2"};
		String para5[] ={ "-l","-a","file.c"};
		main.main(para5);
		System.out.println("test5 is " + test("result.txt",r5));
		//ÑéÖ¤-o¹¦ÄÜ£¬Êä³öÊı¾İ·ÅÔÚoutput.txtÖĞ
		String r6[] = {"file.c,ĞĞÊı£º9", "file.c, ´úÂëĞĞ/¿ÕĞĞ/×¢ÊÍĞĞ£º 6/1/2"};
		String para6[] ={ "-l","-a","file.c","-o","output.txt"};
		main.main(para6);
		System.out.println("test6 is " + test("output.txt",r6));
		//ÑéÖ¤-e¹¦ÄÜ£¬ÊµÏÖÍ£Ö¹µ¥´Ê±í
		String r7[] = {"file.c,µ¥´ÊÊı£º17"};
		String para7[] ={ "-w","file.c","-e","stop.txt"};
		main.main(para7);
		System.out.println("test7 is " + test("result.txt",r7));
		//ÑéÖ¤³ı-sËùÓĞ¹¦ÄÜÒ»ÆğÊ¹ÓÃÊ±£¬³ÌĞòµÄÕıÈ·ĞÔ£¬Êä³öÓë-*µÄÎ»ÖÃÎŞ¹Ø
		String r8[] = {"file.c,×Ö·ûÊı£º91",
				"file.c,µ¥´ÊÊı£º17",
				"file.c,ĞĞÊı£º9",
				"file.c, ´úÂëĞĞ/¿ÕĞĞ/×¢ÊÍĞĞ£º 6/1/2"	};
		String para8[] ={ "-c","-l","-w","-a","file.c","-e","stop.txt","-o","output.txt"};
		main.main(para8);
		System.out.println("test8 is " + test("output.txt",r8));
		//ÑéÖ¤-s¹¦ÄÜ
		String r9[] = {"file.c,ĞĞÊı£º9","file2.c,ĞĞÊı£º2"};
		String para9[] ={ "-s","-l","*.c","-o","output.txt"};
		main.main(para9);
		System.out.println("test9 is " + test("output.txt",r9));
		//ÑéÖ¤ËùÓĞµÄ¹¦ÄÜÒ»ÆğÊ¹ÓÃ
		String r10[] = {"file.c,×Ö·ûÊı£º91",
				"file.c,µ¥´ÊÊı£º17",
				"file.c,ĞĞÊı£º9",
				"file.c, ´úÂëĞĞ/¿ÕĞĞ/×¢ÊÍĞĞ£º 6/1/2",
				"file2.c,×Ö·ûÊı£º12",
				"file2.c,µ¥´ÊÊı£º2",
				"file2.c,ĞĞÊı£º2",
				"file2.c, ´úÂëĞĞ/¿ÕĞĞ/×¢ÊÍĞĞ£º 2/0/0"
>>>>>>> æ ¹æ®ç™½ç›’æµ‹è¯•ä¸­æ›´æ”¹æ˜¯éœ€æ±‚è¿›ä¸€æ­¥ä¸Šä¼ æœ€ç»ˆæ–‡ä»¶
	};
		String para10[] ={ "-c","-l","-w","-a","-s","*.c","-e","stop.txt","-o","output.txt"};
		main.main(para10);
		System.out.println("test10 is " + test("output.txt",r10));
		//å•å…ƒæµ‹è¯•ç»“æŸï¼Œè¯¥å•å…ƒæµ‹è¯•åŸºæœ¬è¦†ç›–äº†æ‰€æœ‰çš„å¯æ‰§è¡Œè¯­å¥
	}
	public static boolean test(String result,String right_result[])
	{
		try
		{
			File file = new File(result);
			BufferedReader reader = new BufferedReader(new FileReader(file)); 
			String tempString = null;	
			int i = 0;//è¯»å–åˆ°æµ‹è¯•æ­£ç¡®ç»“æœçš„ç¬¬å‡ è¡Œ
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
