package xyz.itwill06.aop;

import java.util.List;

public interface HewonService {
	void addHewon(Hewon hewon);
	Hewon getHewon(int num);
	List<Hewon> getHewonList();
}