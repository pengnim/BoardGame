package model;

public class Block implements Comparable<Block>{
	public int num;
	public String color;
	public Block(int n,String c) {
		num=n;
		color=c;
	}
	@Override
	public int compareTo(Block b) {
		int r=num-b.num;
		if(r==0) {
			r=color.compareTo(b.color);
		}
		return r;
	}	

}
