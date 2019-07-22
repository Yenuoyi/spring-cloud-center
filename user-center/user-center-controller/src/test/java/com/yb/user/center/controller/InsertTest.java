package com.yb.user.center.controller;

import com.yb.common.center.thread.pool.ThreadPoolUtil;
import com.yb.user.center.dao.PromotionVoucherInfoDao;
import com.yb.user.center.export.PromotionVoucherInfoDTO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.TransactionIsolationLevel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@SpringBootTest
@RunWith(SpringRunner.class)
public class InsertTest {
    private static ThreadLocal<List> threadLocal = new ThreadLocal<>();
    @Resource
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void insert() throws InterruptedException {
/*        List list = threadLocal.get();
        System.out.println(list);
        threadLocal.set(new LinkedList());
        list = threadLocal.get();
        System.out.println(list);*/
        List<PromotionVoucherInfoDTO> list = generatorProm(10);
        List<List<PromotionVoucherInfoDTO>> lists = cutList(list, 10);
        Long startTime = System.currentTimeMillis();

        ThreadPoolUtil instance = ThreadPoolUtil.getInstance();
        CountDownLatch countDownLatch = new CountDownLatch(lists.size());
        for (int i = 0; i < lists.size(); i++) {
            instance.execute(new executeInsert(lists, i, countDownLatch, instance));
        }
        countDownLatch.await();
        Long endTime = System.currentTimeMillis();
        System.out.println("插入数据用时：" + (endTime - startTime));

    }

    public List<PromotionVoucherInfoDTO> generatorProm(int size) {
        List<PromotionVoucherInfoDTO> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            PromotionVoucherInfoDTO promotionVoucherInfoDTO = new PromotionVoucherInfoDTO();
            promotionVoucherInfoDTO.setCardPrefix("A");
            promotionVoucherInfoDTO.setCreateId(1L);
            promotionVoucherInfoDTO.setCreateName("yebing");
            promotionVoucherInfoDTO.setCreateCode("001");
            promotionVoucherInfoDTO.setCreateTime(new Date());
            promotionVoucherInfoDTO.setActivityStartTime(new Date());
            promotionVoucherInfoDTO.setActivityEndTime(new Date());
            promotionVoucherInfoDTO.setGenerationType(1);
            promotionVoucherInfoDTO.setParticipatePlatform("O2O");
            promotionVoucherInfoDTO.setPeriod(1);
            promotionVoucherInfoDTO.setUseStartTime(new Date());
            promotionVoucherInfoDTO.setUseEndTime(new Date());
            list.add(promotionVoucherInfoDTO);
        }
        return list;
    }

    public List<PromotionVoucherInfoDTO> generatorProm(double size) {
        List<PromotionVoucherInfoDTO> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            PromotionVoucherInfoDTO promotionVoucherInfoDTO = new PromotionVoucherInfoDTO();
            promotionVoucherInfoDTO.setCardPrefix("A");
            promotionVoucherInfoDTO.setCreateId(1L);
            promotionVoucherInfoDTO.setCreateName("yebing");
            promotionVoucherInfoDTO.setCreateCode("001");
            promotionVoucherInfoDTO.setCreateTime(new Date());
            promotionVoucherInfoDTO.setActivityStartTime(new Date());
            promotionVoucherInfoDTO.setActivityEndTime(new Date());
            promotionVoucherInfoDTO.setGenerationType(1);
            promotionVoucherInfoDTO.setParticipatePlatform("O2O");
            promotionVoucherInfoDTO.setPeriod(1);
            promotionVoucherInfoDTO.setUseStartTime(new Date());
            promotionVoucherInfoDTO.setUseEndTime(new Date());
            list.add(promotionVoucherInfoDTO);
        }
        return list;
    }

    public List<List<PromotionVoucherInfoDTO>> cutList(List<PromotionVoucherInfoDTO> list, int size) {
        List<List<PromotionVoucherInfoDTO>> lists = new ArrayList<>();
        for (int i = 0; i < list.size(); i += size) {
            List<PromotionVoucherInfoDTO> promotionVoucherInfoDTOS = list.subList(i, i + size > list.size() ? list.size() : i + size);
            lists.add(promotionVoucherInfoDTOS);
        }
        return lists;
    }

    class executeInsert implements Runnable {
        private List<List<PromotionVoucherInfoDTO>> lists;
        private int index;
        private CountDownLatch countDownLatch;
        private ThreadPoolUtil threadPoolUtil;

        executeInsert(List<List<PromotionVoucherInfoDTO>> lists, int index, CountDownLatch countDownLatch, ThreadPoolUtil threadPoolUtil) {
            this.lists = lists;
            this.index = index;
            this.countDownLatch = countDownLatch;
            this.threadPoolUtil = threadPoolUtil;
        }

        @Override
        public void run() {
            System.out.println("当前index：" + index);
            try{
                SqlSession sqlSession = sqlSessionFactory.openSession(TransactionIsolationLevel.REPEATABLE_READ);
                PromotionVoucherInfoDao mapper = sqlSession.getMapper(PromotionVoucherInfoDao.class);
                mapper.batchSave(lists.get(index));
                sqlSession.commit();
                sqlSession.close();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                countDownLatch.countDown();
            }
        }
    }
}
