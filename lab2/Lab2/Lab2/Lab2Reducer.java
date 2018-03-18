// Atticus Liu
// 1/24/17
// CSC 369 Stanchev
// Lab 2

import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.Reducer.*;

public class Lab2Reducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    @Override
    public void reduce(Text date,
                       Iterable<IntWritable> sales, Context context)
            throws IOException, InterruptedException {

        int count = 0;
        for (IntWritable sale: sales){
            count += 1;
        }
        context.write(date, new IntWritable(count));
    }
}