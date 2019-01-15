package br.com.gameker.webapp;

public class teste {
	public static void main(String args[]){
		StringBuffer daBuffer=new StringBuffer();
		daBuffer.append("Hello World");
		System.out.println(daBuffer.capacity());
		daBuffer.append("All work and no play makes Jack a dull boy");
		System.out.println(daBuffer.capacity());
		}
}
