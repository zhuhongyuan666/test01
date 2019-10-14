package cn.test.main;

import java.util.List;

import cn.test.establishPoker.Poker;

public class TestGame {

	
	public static void main(String[] args) {
		Poker pk=new Poker();//实例化Poker对象
		List<List<String>> deal = pk.Deal(pk.getNewPokers(pk.getPokers()));
		List<String> list = deal.get(1);//deal.get(0):地主   deal.get(1):农民  deal.get(2):农民
		
		System.out.println("======��ӭ�������Ӷ�����======");
		System.err.println("��ʼ����...");
		
		
	}
}
