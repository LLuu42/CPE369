// Atticus Liu
// 1/24/17
// CSC 369 Stanchev
// Lab 2

import org.apache.log4j.*;
import org.apache.hadoop.util.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.apache.hadoop.mapreduce.lib.input.*;

public class Lab2Driver extends Configured implements Tool {
    private static final Logger THE_LOGGER = Logger.getLogger(Lab2Driver.class);

    @Override
    public int run(String[] args) throws Exception {
        Job job = Job.getInstance(); // creates new Hadoop job
        job.setJarByClass(Lab2Driver.class);
        job.setJobName("Lab2Driver"); //same as java class name
        job.setOutputKeyClass(Text.class); //output key class for reduce function
        job.setOutputValueClass(IntWritable.class); //output value class for reduce function
        job.setMapOutputKeyClass(Text.class); //output key class for map function
        job.setMapOutputValueClass(IntWritable.class); //output value class for map function
        job.setMapperClass(Lab2Mapper.class);//sets the mapper
        job.setReducerClass(Lab2Reducer.class);//sets the reducer
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        boolean status = job.waitForCompletion(true); //runs the job, returns true if executed successfully
        THE_LOGGER.info("run(): status=" + status);
        return status ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            throw new IllegalArgumentException("usage: <input> <output>");
        }

        THE_LOGGER.info("inputDir = " + args[0]);
        THE_LOGGER.info("outputDir = " + args[1]);
        int returnStatus = ToolRunner.run(new Lab2Driver(), args);
        THE_LOGGER.info("returnStatus=" + returnStatus);
        System.exit(returnStatus);
    }
}