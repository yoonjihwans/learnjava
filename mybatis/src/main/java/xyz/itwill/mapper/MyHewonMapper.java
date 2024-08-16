package xyz.itwill.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import xyz.itwill.dto.MyHewon;

public interface MyHewonMapper {
	int insertHewon(MyHewon hewon);
	List<MyHewon> selectHewonList();
	List<MyHewon> selectDiscriminatorHewonList();
	List<MyHewon> selectScopeHewonList(int scope);
	String selectDtoHewonId(MyHewon hewon);
	//HashMap 클래스(Map 인터페이스)를 매개변수로 작성할 경우 맵키의 제네릭은 String 클래스로
	//설정하고 맵값의 제네릭은 Object 객체로 설정하여 사용
	String selectMapHewonId(Map<String, Object> map);
	//@Param : 추상메소드의 매개변수에 저장된 값을 XML 기반의 매퍼파일에 작성된 엘리먼트의 
	//SQL 명령으로 사용할 수 있도록 제공하는 어노테이션
	//value 속성 : 매개변수에 저장된 값을 SQL 명령에서 사용할 수 있는 이름을 속성값으로 설정
	// => 다른 속성이 없는 경우 속성값만 설정 가능
	String selectParamHewonId(@Param(value="name") String name,@Param("email") String email);
	int insertMapHewon(Map<String, Object> map);
	List<Map<String, Object>> selectMapHewonList();
	List<MyHewon> selectSearchHewonList(Map<String, Object> map);
	List<MyHewon> selectDynamicNameHewonList(String name);
	List<MyHewon> selectDynamicIdNameHewonList(Map<String, Object> map);
	int updateHewon(MyHewon hewon);
	int updateDynamicHewon(MyHewon hewon);
	List<MyHewon> selectMultiIdDynamicHewonList(List<String> list); 
}
