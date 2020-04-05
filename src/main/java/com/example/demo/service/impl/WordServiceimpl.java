package com.example.demo.service.impl;

import com.example.demo.entity.WordEntity;
import com.example.demo.mapper.WordMapper;
import com.example.demo.service.WordService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WordServiceimpl implements WordService {
    @Autowired
    private WordMapper word;

    @Override
    public List<WordEntity> findWord(String open_id) {
        List<WordEntity> list=word.findWord(open_id);
        return list;
    }

    @Override
    public Map<String,Integer> findCount(String open_id, String set_name) {
//        String table_name="words_temp_"+open_id+"_"+book_id;
//        word.update_temptable(open_id, book_id,table_name);
        Map<String,Integer> wordmap = null;
        String sql="SELECT\n" +
                "\tcount(word_set_map.entry_id) as word_number\n" +
                "FROM\n" +
                "\tword_set_map\n" +
                "\tINNER JOIN\n" +
                "\tword_set\n" +
                "\tON \n" +
                "\t\tword_set_map.set_id = word_set.set_id\n" +
                "WHERE\n" +
                "\tword_set.set_name = '%2$s' and \n" +
                "\tword_set_map.entry_id not in \n" +
                "\t(select word_set_map.entry_id from word_set_map \n" +
                "\t\twhere word_set_map.set_id in \n" +
                "\t\t((SELECT\n" +
                "\t\t\t\tvocabulary.set_id\n" +
                "\t\t\tFROM\n" +
                "\t\t\t\tvocabulary\n" +
                "\t\t\tWHERE\n" +
                "\t\t\t\tvocabulary.open_id = '%1$s')\n" +
                "\t\t\t\tUNION\n" +
                "\t\t\t(SELECT\n" +
                "\t\t\t\tword_set.set_id\n" +
                "\t\t\tFROM\n" +
                "\t\t\t\tword_set\n" +
                "\t\t\tWHERE\n" +
                "\t\t\t\tword_set.set_name = '掌握' AND\n" +
                "\t\t\t\tword_set.creator_id = '%1$s'))\n" +
                ")";
        sql+=String.format("('%s','%s')", open_id,set_name);

        wordmap.put("word",word.wordCount(sql));
        //wordmap.put("grammar",word.grammarCount(open_id, book_id,table_name));
        return wordmap;
    }//返回一本书中未掌握单词数和语法数

    @Override
    public Map<String,Integer> findCount_unlogin(int book_id) {
        Map<String,Integer> map=null;
        map.put("word",word.findCount_unlogin(book_id));
        return map;
    }

    @Override
    public List<WordEntity> finW_list(String open_id, int book_id) {
        //word.findGrammar_list(open_id,book_id,sort,order);
        String sql="SELECT\n" +
                "\tword.*\n" +
                "FROM\n" +
                "\tword\n" +
                "\tINNER JOIN\n" +
                "\tword_set_map\n" +
                "\tON \n" +
                "\t\tword.word_id = word_set_map.entry_id\n" +
                "\tINNER JOIN\n" +
                "\tword_set\n" +
                "\tON \n" +
                "\t\tword_set_map.set_id = word_set.set_id\n" +
                "WHERE\n" +
                "\tword_set.set_name = '%2$s' and\n" +
                "\t\tword.word_id not in \n" +
                "\t(select word_set_map.entry_id from word_set_map \n" +
                "\t\twhere word_set_map.set_id in \n" +
                "\t\t((SELECT\n" +
                "\t\t\t\tvocabulary.set_id\n" +
                "\t\t\tFROM\n" +
                "\t\t\t\tvocabulary\n" +
                "\t\t\tWHERE\n" +
                "\t\t\t\tvocabulary.open_id = '%1$s')\n" +
                "\t\t\t\tUNION\n" +
                "\t\t\t(SELECT\n" +
                "\t\t\t\tword_set.set_id\n" +
                "\t\t\tFROM\n" +
                "\t\t\t\tword_set\n" +
                "\t\t\tWHERE\n" +
                "\t\t\t\tword_set.set_name = '掌握' AND\n" +
                "\t\t\t\tword_set.creator_id = '%1$s'))\n" +
                ")\n" +
                "limit 20 offset %3$s";
        sql+=String.format("('%s','%s')", open_id,book_id);
        System.out.println("这是登陆的word");
        return word.findWord_list(sql);
    }

    @Override
    public List<WordEntity> findW_list_unlogin(int book_id) {
        System.out.println("这是没登录的word");
       return word.findWord_list_unlogin(book_id);

    }

//    @Override
//    public int Count(String open_id, int book_id) {
//        String table_name="words_temp_"+open_id+"_"+book_id;
//        word.update_temptable(open_id, book_id,table_name);
//
//        return word.wordCount(open_id, book_id,table_name);
//        //wordmap.put("grammar",word.grammarCount(open_id, book_id,table_name));
//
//    }//返回一本书中未掌握单词数和语法数

    @Override
    public List<WordEntity> word(int word_id) {
        return word.word(word_id);
    }

    @Override
    public List<WordEntity> findWord_in_book(int book_id,int index) {
        return word.findWord_in_book(book_id,index);
    }

    @Override
    public List<WordEntity> word_set(int set_id,int index) {

        return word.word_set(set_id,index);
    }
}
