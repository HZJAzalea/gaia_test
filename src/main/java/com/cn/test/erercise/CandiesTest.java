package com.cn.test.erercise;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: 1
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 1 1
 * @Version 1.0
 */
public class CandiesTest {

        public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        /*
        1、比较每个孩子的糖果数目，得出最大值
        2、每个孩子的糖果数加上额外糖果数，与最大值进行比较，大于等于最大值则为true
        */
            int max = 0;
            for(int i=0;i<candies.length;i++){
                if(candies[i]>=max){
                    max = candies[i];//max=4
                }
            }
            System.out.println("max:" + max);
            /*
            i=0时，candies[i]=4,candies[i+1]=2,max=4;
            i=1时，candise[i]=2,
             */


            List<Boolean> list = new ArrayList<Boolean>();
            Boolean isMaxCandiesNum = false;
            for(int i=0;i<candies.length;i++){
                if((candies[i]+extraCandies)>=max ){
                    isMaxCandiesNum = true;
                }else{
                    isMaxCandiesNum = false;
                }
                list.add(isMaxCandiesNum);
            }
            System.out.println(list);
            return list;

        }

    public static void main(String[] args) {
        CandiesTest ct = new CandiesTest();
        int[] candies = {4,2,1,1,2};
        int extraCandies = 1;
        ct.kidsWithCandies(candies,extraCandies);
    }

}
