// Atticus Liu
// 1/24/17
// CSC 369 Stanchev
// Lab 2

import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.Mapper.*;
import org.apache.log4j.Logger;

public class Lab2Mapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private static Logger THE_LOGGER = Logger.getLogger(Lab2Mapper.class);

    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {

        THE_LOGGER.debug("I AM IN LOGGER");
        String valueAsString = value.toString().trim();
        String[] tokens = valueAsString.split(" ");
        if (tokens.length != 5) {
            return;
        }
        context.write(new Text(tokens[1]), new IntWritable(1));
    }
}