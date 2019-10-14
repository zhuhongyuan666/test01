package cn.test.establishPoker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * 创建一副54张的扑克牌 A：黑桃 B：红桃 C：梅花 D：方片
 * 
 * 扑克原始值 映射值 3-10 3-10数字 J 11 Q 12 K 13 A 14 2 15 小王 16 大王 17
 * 
 * Long num = System.currentTimeMillis(); 时间戳函数
 * 
 * @author Administrator
 *
 * @date 2019-08-24 14:04:29
 *
 * @version 1.0
 *
 *
 */
public class Poker {

	// 生成新牌
	public String[] getPokers() {

		String[] Pokers = new String[54];// 初始化54个位置的数组放牌
		int PokerIndex = 0; // 索引

		for (int i = 3; i <= 16; i++) {
			if (i == 16) {
				Pokers[PokerIndex] = "16";
				Pokers[PokerIndex + 1] = "17";
			} else {
				Pokers[PokerIndex] = "♠" + i;
				Pokers[PokerIndex + 1] = "♥" + i;
				Pokers[PokerIndex + 2] = "♣" + i;
				Pokers[PokerIndex + 3] = "♦" + i;
				PokerIndex += 4; // 每初始化一套♠♥♣♦单值牌，索引移4位
			}
		}
		return Pokers;
	}

	// 洗牌
	public String[] getNewPokers(String[] Pokers) {

		Random rd = new Random();
		for (int i = 0; i < 54; i++) {
			int j = rd.nextInt(54);// 生成随机数
			String temp = Pokers[i];// 交换
			Pokers[i] = Pokers[j];
			Pokers[j] = temp;
		}

		return Pokers;
	}

	// 发牌:传入洗好的牌,返回底牌以及三位玩家的牌
	public List<List<String>> Deal(String[] NewPokers) {
		int PKarray = ((int) (Math.random() * 10000) % 3);// 随机产生0,1,2数字 PKarray==0 : jeck地主 pony==1 : jeck地主 else
															// robin:地主
		List<List<String>> list = new ArrayList<List<String>>();
		List<String> dipai = new ArrayList<String>();// 底牌
		List<String> jeck = new ArrayList<String>();
		List<String> pony = new ArrayList<String>();
		List<String> robin = new ArrayList<String>();

		for (int i = 0; i < NewPokers.length - 3; i++) {
			if (i % 3 == 0) {
				jeck.add(NewPokers[i]); // 地主
			}
			if (i % 3 == 1) {
				pony.add(NewPokers[i]);
			}
			if (i % 3 == 2) {
				robin.add(NewPokers[i]);
			}
		}
		// 底牌
		dipai.add(NewPokers[51]);
		dipai.add(NewPokers[52]);
		dipai.add(NewPokers[53]);

		for (int i = 0; i < 3; i++) {
			if (PKarray == 0) {
				jeck.add(dipai.get(i));
			} else if (PKarray == 1) {
				pony.add(dipai.get(i));
			} else {
				robin.add(dipai.get(i));
			}
		}

		// 把三个玩家的牌和底牌放入list

		list.add(jeck);
		list.add(pony);
		list.add(robin);
		// list.add(dipai);

		/*
		 * System.out.println(pony); System.out.println(jeck);
		 * System.out.println(robin); System.out.println(dipai);
		 */
		return list;
	}

	// 给扑克牌排序
	public List<String> SortPoker(List<String> Pokers) {
		List<String> temp = new ArrayList<String>();
		for (int i = 3; i < 16; i++) {
			// 取出红桃牌
			if (Pokers.indexOf("♥" + i) != -1) {
				temp.add(Pokers.get(Pokers.indexOf("♥" + i)));
				Pokers.remove(Pokers.indexOf("♥" + i));
			} // 取出黑桃牌
			if (Pokers.indexOf("♠" + i) != -1) {
				temp.add(Pokers.get(Pokers.indexOf("♠" + i)));
				Pokers.remove(Pokers.indexOf("♠" + i));
			} // 取出梅花牌
			if (Pokers.indexOf("♣" + i) != -1) {
				temp.add(Pokers.get(Pokers.indexOf("♣" + i)));
				Pokers.remove(Pokers.indexOf("♣" + i));
			} // 取出方块牌
			if (Pokers.indexOf("♦" + i) != -1) {
				temp.add(Pokers.get(Pokers.indexOf("♦" + i)));
				Pokers.remove(Pokers.indexOf("♦" + i));
			}
		}
		if (Pokers.indexOf("16") != -1) {
			temp.add((temp.size()), Pokers.get(Pokers.indexOf("16")));
		}

		if (Pokers.indexOf("17") != -1) {
			temp.add((temp.size()), Pokers.get(Pokers.indexOf("17")));
		}
		return temp;
	}

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		Poker w = new Poker();
		List<List<String>> deal = w.Deal(w.getNewPokers(w.getPokers()));
		/*
		 * List<String> list = deal.get(1); synchronized (list) {
		 * System.out.println("排序前"); for (String string : list) {
		 * System.err.print(string); } }
		 * 
		 * System.out.println(); System.out.println(); System.out.println();
		 * System.out.println("\n排序后"); List<String> getlist = w.SortPoker(list); for
		 * (String string : getlist) { System.err.print(string); }
		 * 
		 * System.out.println(); System.out.println(); System.out.println(); String[]
		 * pokers = w.getPokers(); for (int i = 0; i < pokers.length; i++) { //
		 * System.out.print(pokers[i]+"\t"); if (i % 4 == 0) { System.out.println(); }
		 * System.out.print(pokers[i] + "\t"); } System.out.println(); String[] pokerss
		 * = w.getNewPokers(w.getPokers()); for (int i = 0; i < pokerss.length; i++) {
		 * if (i % 4 == 0) { System.out.println(); } System.out.print(pokerss[i] +
		 * "\t"); }
		 */

		List<String> list1 = w.SortPoker(deal.get(0));
		for (String string : list1) {
			System.err.print(string + " ");
		}
		System.err.println("\n输入:");
		String next1 = in.next();

		List<String> list2 = w.SortPoker(deal.get(1));
		for (String string : list2) {
			System.err.print(string + " ");
		}
		System.err.println("\n输入:");
		String next2 = in.next();

		List<String> list3 = w.SortPoker(deal.get(2));
		for (String string : list3) {

			System.err.print(string + " ");
		}
	}
}