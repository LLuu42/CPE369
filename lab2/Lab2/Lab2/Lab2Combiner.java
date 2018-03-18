import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class Lab2Combiner  extends Reducer<Text, IntWritable, Text, IntWritable> {
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
