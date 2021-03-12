package Leetcode.KMP;
import Leetcode.everyday.day20210111.Solution;

public class Main {
	//实测  KMP 与 indexOf
	public static void main(String[] args) {
		String str = "hdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfdzgvrvdfzgabcabcababacccdfsdgffsdGcxfvfdfsfdgsdfedsf";
		String match = "dgffsdGcxfvfdfsfdgsdfedsfhdgzfsdgzszgfd";
		long t1 = System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++) {
		getIndexOf(str, match);
		}
		long t2 = System.currentTimeMillis();
		System.out.println("KMP:"+(t2-t1));

		t1 = System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++) {
			Solution.strStr(str, match);
		}
		t2 = System.currentTimeMillis();
		System.out.println("strStr:"+(t2-t1));

		
		t1 = System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++) {
		str.indexOf(match);
		}
		t2 = System.currentTimeMillis();
		System.out.println("indexof:"+(t2-t1));
 
		
	}
	
	public static int getIndexOf(String s,String m) {
		if(s==null||m==null||m.length()<1||m.length()>s.length())
			return -1;
		
		char[] ss = s.toCharArray();
		char[] ms = m.toCharArray();
		
		int si = 0;
		int mi = 0;
		int[] next = getNextArray(ms);
		
		while(si<ss.length&&mi<ms.length) {
			if(ss[si]==ms[mi]) {
				si++;
				mi++;
			}else if(next[mi]==-1) {
				si++;
			}else {
				mi = next[mi];
			}
			
		}
		return mi==ms.length?si-mi:-1;
	}
 
	public static int[] getNextArray(char[] ms) {
		if(ms.length==1) {
			return new int[] {-1};
		}
		int[] next = new int[ms.length];
		next[0]=-1;
		next[1]=0;
		int pos = 2;
		int cn = 0;
		
		while(pos<ms.length) {
			if(ms[pos-1]==ms[cn]) {
				next[pos++] = ++cn;
			}else if(cn>0) {
				cn = next[cn];
			}else {
				next[pos++] = 0;
			}	
		}	
		return next;
 
	}
 
}