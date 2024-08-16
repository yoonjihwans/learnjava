package xyz.itwill.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import xyz.itwill.dto.MyHewon;

//mybatis 프레임워크는 Interface 기반의 매퍼파일만 사용해 맵퍼로 등록 가능
// => 인터페이스의 추상메소드에 매퍼 어노테이션을 사용해 SQL 명령 등록
public interface MyHewonInterfaceMapper {
	//추상메소드의 @Select 어노테이션으로 등록된 SELECT 명령으로 검색된 행은 자동 매핑 
	//처리하여 Java 객체로 제공 - 검색행의 컬럼명과 DTO 클래스의 필드명이 같도록 설정
	// => 검색행의 컬럼명과 DTO 클래스의 필드명이 모두 다른 경우 DTO 객체 대신 NULL 제공
	//@Results : 검색행을 Java 객체로 제공하기 위한 어노테이션 - 속성을 사용해 매핑 정보 제공
	// => XML 기반의 매퍼파일에서 resultMap 엘리먼트와 유사한 기능 제공
	//value 속성 : 검색행의 컬럼값이 객체 필드에 저장하기 위한 정보를 속성값으로 설정
	// => 검색행의 컬럼값을 객체 필드에 저장하기 위한 매핑 관련 어노테이션(@ConstructorArgs
	//, @Result, @One, @Many 등)을 사용 
	// => 다수의 매핑 정보를 제공하기 위해 value 속성값으로 배열 사용 가능
	// => value 속성외에 다른 속성이 없는 경우 속성값만 설정 가능 
	@Results(value = {
			//@Result : 검색행의 컬럼값을 객체 필드에 저장하기 위한 어노테이션
			// => XML 기반의 매퍼파일에서 id 엘리먼트 또는 result 엘리먼트와 유사한 기능 제공
			//column 속성 : 검색행의 컬럼명을 속성값으로 설정
			//property 속성 : 객체의 필드명을 속성값으로 설정
			@Result(column = "hewon_id", property = "id")
			, @Result(column = "hewon_name", property = "name")
			, @Result(column = "hewon_phone", property = "phone")
			, @Result(column = "hewon_email", property = "email")
			, @Result(column = "hewon_scope", property = "scope")})
	@Select("select hewon_id, hewon_name, hewon_phone, hewon_email, hewon_scope"
			+ " from myhewon order by hewon_id")
	List<MyHewon> selectHewonList();

	//@Results 어노테이션은 재사용 불가능 - 유지보수의 효율성 감소
	@Results(value = {
			@Result(column = "hewon_id", property = "id")
			, @Result(column = "hewon_name", property = "name")
			, @Result(column = "hewon_phone", property = "phone")
			, @Result(column = "hewon_email", property = "email")
			, @Result(column = "hewon_scope", property = "scope")})
	//@Select 엘리먼트의 value 속성값으로 배열을 설정해 SQL 명령을 배열 요소값으로 나누어 작성 가능 
	@Select({"select hewon_id, hewon_name, hewon_phone, hewon_email, hewon_scope"
		, " from myhewon where hewon_name=#{name} order by hewon_id"})
	List<MyHewon> selectNameHewonList(String name);
	
	/*
	@Results(value = {
			@Result(column = "hewon_id", property = "id")
			, @Result(column = "hewon_name", property = "name")
			, @Result(column = "hewon_phone", property = "phone")
			, @Result(column = "hewon_email", property = "email")
			, @Result(column = "hewon_scope", property = "scope")})
	//@SelectProvider : 클래스(SQL Builder 클래스)의 메소드를 호출하여 SELECT 명령을 반환받아
	//등록하는 어노테이션 - 동적 SQL 기능 구현을 위해 사용
	//SQL Builder 클래스 : SQL 객체에 저장된 SQL 명령을 반환하는 메소드가 작성된 클래스
	// => SQL 객체의 메소드를 호출하여 SQL 명령을 작성해 SQL 객체에 저장
	//type 속성 : SQL Builder 클래스의 Class 객체를 속성값으로 설정
	//method 속성 : SQL Builder 클래스에서 SQL 명령을 반환하는 메소드의 이름을 속성값으로 설정
	@SelectProvider(type = MyHewonProvider.class, method = "selectDynamicName")
	List<MyHewon> selectDynamicNameHewonList(String name);
	*/
	
	@Results(value = {
			@Result(column = "hewon_id", property = "id")
			, @Result(column = "hewon_name", property = "name")
			, @Result(column = "hewon_phone", property = "phone")
			, @Result(column = "hewon_email", property = "email")
			, @Result(column = "hewon_scope", property = "scope")})
	//@Select 어노테이션의 value 속성값에 script 엘리먼트를 사용하면 SQL 명령 작성시 동적
	//SQL 기능 구현 관련 엘리먼트 사용 가능
	@Select({"<script>select hewon_id, hewon_name, hewon_phone, hewon_email, hewon_scope"
		, " from myhewon <if test=\"name != null and name != ''\"> where hewon_name=#{name}"
		, "</if>", " order by hewon_id</script>"})
	List<MyHewon> selectDynamicNameHewonList(String name);
}