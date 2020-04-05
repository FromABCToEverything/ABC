package com.example.demo.controller;

import com.example.demo.entity.BookSetEntity;
import com.example.demo.entity.WordSetEntity;
import com.example.demo.mapper.SessionMapper;
import com.example.demo.service.impl.SessionServiceimpl;
import org.apache.commons.collections.MapIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static com.example.demo.DemoApplication.sessionMap;

@RestController
public class SessionController {
	@Autowired
	private SessionServiceimpl session;

	@PostMapping("/vocabulary")
    public String add_vocabulary(String sets,String session_id) {

    	String sql="INSERT INTO vocabulary (open_id,set_id,set_name) VALUES";
    	//待实现：获取open_id
    	//String open_id="oZb_Lswwm-DD6cnP5QH16fk1IWBw";
    	//String open_id=session.findOpen_id(session_id);
		String open_id= (String) sessionMap.get(session_id);
    	//生成批量插入形式的sql
    	String[] temp = sets.split(";");
    	for(int i=0;i<temp.length-1;i+=2) {
    		sql+=String.format("('%s',%s,'%s'),", open_id,temp[i],temp[i+1]);
    	}
    	//去掉末尾逗号
    	sql=sql.substring(0,sql.length()-1);
    	System.out.println(sql);


    	//待实现：执行sql，返回成功或是失败
    	
    	boolean ok = false;
    	try {
    		//待实现：执行sql语句，返回成功或是失败
			int op=session.add_vocabulary(sql);//执行sql，失败int值为0
			System.out.println("这是op"+op);
			if (op!=0)
    		ok=true;
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return String.valueOf(ok);
    }
    
    @PostMapping("/plan")
    public String add_plan(String set_id,String set_name,String session_id) {
    	//待实现：获取open_id
    	//String open_id="oZb_Lswwm-DD6cnP5QH16fk1IWBw";
		//String open_id=session.findOpen_id(session_id);
		String open_id= (String) sessionMap.get(session_id);
    	//插入计划里各单词的进度信息
    	String format = "insert into progress\r\n" + 
    			"SELECT\r\n" + 
    			"	'%1$s'as open_id,\r\n" + 
    			"	words.word_id as word_id,\r\n" + 
    			"	CURRENT_DATE as last_review_date,\r\n" + 
    			"	%3$s as span,\r\n" + 
    			"	DATE_ADD(CURRENT_DATE,INTERVAL FLOOR(power(2,%3$s-1)) day) as start_date,\r\n" + 
    			"	DATE_ADD(CURRENT_DATE,INTERVAL FLOOR(power(2,%3$s-1)+power(2,%3$s)) day) as end_date\r\n" + 
    			"FROM\r\n" + 
    			"		(select distinct t.word_id as word_id\r\n" + 
    			"		from \r\n" + 
    			"			(select word_set_map.entry_id as word_id from word_set_map \r\n" + 
    			"			where word_set_map.set_id = %2$s) as t\r\n" + 
    			"		where t.word_id not in \r\n" + 
    			"			(select word_set_map.entry_id from word_set_map \r\n" + 
    			"				where word_set_map.set_id in \r\n" + 
    			"				((SELECT\r\n" + 
    			"						vocabulary.set_id\r\n" + 
    			"					FROM\r\n" + 
    			"						vocabulary\r\n" + 
    			"					WHERE\r\n" + 
    			"						vocabulary.open_id = '%1$s')\r\n" + 
    			"						UNION\r\n" + 
    			"					(SELECT\r\n" + 
    			"						word_set.set_id\r\n" + 
    			"					FROM\r\n" + 
    			"						word_set\r\n" + 
    			"					WHERE\r\n" + 
    			"						word_set.set_name = '掌握' AND\r\n" + 
    			"						word_set.creator_id = '%1$s')\r\n" + 
    			"						UNION\r\n" + 
    			"					(SELECT\r\n" + 
    			"						plan.set_id\r\n" + 
    			"					FROM\r\n" + 
    			"						plan\r\n" + 
    			"					WHERE\r\n" + 
    			"						plan.open_id = '%1$s'))\r\n" + 
    			"			)\r\n" + 
    			"		) as words\r\n" + 
    			"GROUP BY\r\n" + 
    			"	words.word_id\r\n" + 
    			"ORDER BY\r\n" + 
    			"	words.word_id ASC";
    	String sql1 = String.format(format,open_id,set_id,-1);
    	//插入单词计划
    	String sql2=String.format("insert into plan (open_id,set_id,set_name,weekly_reviewed) VALUES('%1$s',%2$s,'%3$s','0,0,0,0,0,0,0')", open_id,set_id,set_name);
    	
    	System.out.println(sql1);
    	System.out.println(sql2);
    	boolean ok = false;
    	try {
    		//待实现：执行sql语句，返回成功或是失败
    		//先执行sql1，然后执行sql2，顺序一定不能颠倒
			int op1=session.add_process(sql1);
			if(op1!=0) {
				op1 = session.add_plan(sql2);
				if ((op1 != 0)) {
					ok = true;
				}
			}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return String.valueOf(ok);
    }
    
    @GetMapping("/plan")
    public List<WordSetEntity> get_plans(String session_id) {
    	//待实现：获取open_id
    	//String open_id="oZb_Lswwm-DD6cnP5QH16fk1IWBw";
		//String open_id=session.findOpen_id(session_id);
		String open_id= (String) sessionMap.get(session_id);
    	String format = "SELECT\r\n" +
    			"	word_set.*\r\n" +
    			"FROM\r\n" +
    			"	word_set\r\n" +
    			"	INNER JOIN\r\n" +
    			"	plan\r\n" +
    			"	ON \r\n" +
    			"		word_set.set_id = plan.set_id\r\n" +
    			"WHERE\r\n" +
    			"	plan.open_id = '%s'";
    	String sql = String.format(format, open_id);
//    	System.out.println(sql);

    	return session.select_plan(sql);
    }
    
//    @DeleteMapping("/plan")
//    public String delete_plan(String set_id,String session_id) {
//    	
//    	return "suc";
//    }
    
    @GetMapping("/progress")
    public List<Map<String,Object>> get_progress(String set_id,String session_id) {
    	//待实现：获取open_id
    	//String open_id="oZb_Lswwm-DD6cnP5QH16fk1IWBw";
		//String open_id=session.findOpen_id(session_id);
		String open_id= (String) sessionMap.get(session_id);
    	//获取该计划里用户仍未开始练习的单词数量、待复习单词总数、处在各复习间隔期间的单词数量、过去一周每天的单词复习数量
    	String format = "select * from (SELECT\r\n" + 
    			"	count(*) as n\r\n" + 
    			"FROM\r\n" + 
    			"	progress\r\n" + 
    			"	INNER JOIN\r\n" + 
    			"	word_set_map\r\n" + 
    			"	ON \r\n" + 
    			"		progress.word_id = word_set_map.entry_id\r\n" + 
    			"	INNER JOIN\r\n" + 
    			"	plan\r\n" + 
    			"	ON \r\n" + 
    			"		word_set_map.set_id = plan.set_id\r\n" + 
    			"WHERE\r\n" + 
    			"	plan.open_id = '%1$s' AND\r\n" + 
    			"	plan.set_id = %2$s	AND\r\n" + 
    			"	progress.span = -1) as n,\r\n" + 
    			"	(select count(*) as t from word_set_map where word_set_map.set_id=%2$s) as t,\r\n" + 
    			"(SELECT\r\n" + 
    			"	count(*) as r\r\n" + 
    			"FROM\r\n" + 
    			"	progress\r\n" + 
    			"	INNER JOIN\r\n" + 
    			"	word_set_map\r\n" + 
    			"	ON \r\n" + 
    			"		progress.word_id = word_set_map.entry_id\r\n" + 
    			"	INNER JOIN\r\n" + 
    			"	plan\r\n" + 
    			"	ON \r\n" + 
    			"		word_set_map.set_id = plan.set_id\r\n" + 
    			"WHERE\r\n" + 
    			"	plan.open_id = '%1$s' AND\r\n" + 
    			"	plan.set_id = %2$s	AND\r\n" + 
    			"	progress.span > -1 AND\r\n" + 
    			"	progress.span < 7) as r,";
    	
    	String span_format = "(SELECT\r\n" + 
    			"	count(*) as i%1$s\r\n" + 
    			"FROM\r\n" + 
    			"	progress\r\n" + 
    			"	INNER JOIN\r\n" + 
    			"	word_set_map\r\n" + 
    			"	ON \r\n" + 
    			"		progress.word_id = word_set_map.entry_id\r\n" + 
    			"	INNER JOIN\r\n" + 
    			"	plan\r\n" + 
    			"	ON \r\n" + 
    			"		word_set_map.set_id = plan.set_id\r\n" + 
    			"WHERE\r\n" + 
    			"	plan.open_id = '%3$s' AND\r\n" + 
    			"	plan.set_id = %2$s	AND\r\n" + 
    			"	progress.span =%1$s) as t%1$s,";
    	for(int i=0;i<7;i++) {
    		format+=String.format(span_format, i,set_id,open_id);
    	}
    	format+="(select weekly_reviewed as w from plan where plan.set_id=%2$s) as p";
    	
    	String sql = String.format(format, open_id,set_id);
    	//System.out.println(sql);
    	//待实现：执行sql，并返回json格式的结果集
    	return session.select_1(sql);
    }
    
    @GetMapping("/plan_words")
    public List<Map<String,Object>> get_words(String set_id,String page,String session_id) {
    	//待实现：获取open_id
    	//String open_id="oZb_Lswwm-DD6cnP5QH16fk1IWBw";
		//String open_id=session.findOpen_id(session_id);
		String open_id= (String) sessionMap.get(session_id);
    	String format = "select word.*,\r\n" + 
    			"	temp.rest_day \r\n" + 
    			"FROM\r\n" + 
    			"	word,\r\n" + 
    			"(SELECT\r\n" + 
    			"	progress.word_id,\r\n" + 
    			"	DATEDIFF(progress.end_date,CURRENT_DATE) as rest_day\r\n" + 
    			"FROM\r\n" + 
    			"	progress\r\n" + 
    			"WHERE\r\n" + 
    			"	progress.open_id = '%1$s' AND\r\n" + 
    			"	progress.start_date <= CURRENT_DATE AND\r\n" + 
    			"	progress.word_id in (SELECT\r\n" + 
    			"	word_set_map.entry_id\r\n" + 
    			"FROM\r\n" + 
    			"	word_set_map\r\n" + 
    			"WHERE\r\n" + 
    			"	word_set_map.set_id = %2$s)) as temp\r\n" + 
    			"WHERE word.word_id =temp.word_id\r\n" + 
    			"order by temp.rest_day\r\n" + 
    			"asc\r\n" + 
    			"limit %3$s,20";
    	String sql = String.format(format,open_id,set_id,Integer.parseInt(page)*20);
    	System.out.println(sql);
    	

    	return session.select_1(sql);
    
    }

	@GetMapping(path = "/open_id")
	public String findOpen_id(String session_id)//通过session_id获取open_id
	{
		return session.findOpen_id(session_id);
	}

//	@PostMapping(path = "/test")
//	public void test(String open_id,String session_id)
//	{
//		session.resetSession(open_id, session_id);
//	}

	@PostMapping(path = "/test")
	public void test()
	{
		MapIterator map=sessionMap.mapIterator();

		String sql="insert into session (open_id,session_id) values ";
		while (map.hasNext())
		{
			String session_id= (String) map.next();
			String open_id= (String) map.getValue();
			sql+=String.format("('%s','%s'),", open_id,session_id);

		}
		//去掉末尾逗号
		sql=sql.substring(0,sql.length()-1);
		session.resetSession(sql);
		System.out.println("把数据写回session");
	}

}
