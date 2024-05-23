package xyz.itwill.realization;

public class PrintMulti implements Printable{

	@Override
	public void print() {
		System.out.println("복합기 문서 출력");
		
	}
	
	@Override
	public void scan() {
		System.out.println("복합기 문서 스캔");
	}
	
	

}
