import java.util.ArrayList;

public class TextJustification {
	public ArrayList<String> fullJustify(String[] words, int L) {
		ArrayList<String> ret = new ArrayList<String>();
		int length = words.length;
		if (length == 0) return ret;
		int start = 0, end = start;
		int len = 0;
		while (start < length) {
			StringBuffer line = new StringBuffer();
			while (end < length) {
				int sl = words[end].length();
				if (len + (end - start) + sl > L) {
					break;
				}
				len += sl;
				end++;
			}
			end--;
			if (end < start) {
				end = start;
			}
			if (start == end) {
				line.append(words[start]);
				int spaceCount = L - words[start].length();
				for (int i = 0; i < spaceCount; i++) {
					line.append(' ');
				}
				ret.add(line.toString());
			} else {
				boolean lastLine = end == length - 1;
				int spaceBase = lastLine ? 1 : (L - len) / (end - start);
				int bonus = lastLine ? 0 : L - len - spaceBase * (end - start);
				line.append(words[start]);
				for (int i = start + 1; i <= end; i++) {
					for (int j = 0; j < spaceBase; j++) {
						line.append(' ');
					}
					if (bonus > 0) {
						line.append(' ');
						bonus--;
					}
					line.append(words[i]);
				}
				if (lastLine) {
					for (int i = 0; i < L - len - (end - start); i++) {
						line.append(' ');
					}
				}
				ret.add(line.toString());
			}
			start = end + 1;
			end = start;
			len = 0;
		}
		return ret;
	}
}

“这 道题属于纯粹的字符串操作，要把一串单词安排成多行限定长度的字符串。
主要难点在于空格的安排，首先每个单词之间必须有空格隔开，而当当前行放不下更多的 单词并且字符又不能填满长度L时，
我们要把空格均匀的填充在单词之间。如果剩余的空格量刚好是间隔倍数那么就均匀分配即可，否则还必须把多的一个空格放到 前面的间隔里面。
实现中我们维护一个count计数记录当前长度，超过之后我们计算共同的空格量以及多出一个的空格量，然后将当行字符串构造出来。
最后一 个细节就是最后一行不需要均匀分配空格，句尾留空就可以，所以要单独处理一下。时间上我们需要扫描单词一遍，
然后在找到行尾的时候在扫描一遍当前行的单 词，不过总体每个单词不会被访问超过两遍，所以总体时间复杂度是O(n)。
而空间复杂度则是结果的大小（跟单词数量和长度有关，不能准确定义，如果知道最 后行数r，则是O(r*L)）

 代码如下：


public ArrayList<String> fullJustify(String[] words, int L) {
    ArrayList<String> res = new ArrayList<String>();
    if(words==null || words.length==0)
        return res;
    int count = 0;
    int last = 0;
    for(int i=0;i<words.length;i++){
        //count是上一次计算的单词的长度，words[i].length()是当前尝试放的一个单词的长度，
        //假设当前放上了这个单词，那么这一行单词跟单词间的间隔数就是i-last
        //判断这些总的长度加起来是不是大于L（超行数了）
// 这里是以超过它为界限，而不是小于它为界限！		
        if(count + words[i].length() + (i-last) > L){
            int spaceNum = 0;
            int extraNum = 0;
            //因为尝试的words[i]失败了，所以间隔数减1.此时判断剩余的间隔数是否大于0
            if( i-last-1 >0){
                //是间隔的倍数（为啥要减1，因为尝试当前words[i]后发现比L长了，
                //所以当前这个单词不能算作这行，所以间隔就减少一个
                spaceNum = (L-count)/(i-last-1);
                extraNum = (L-count)%(i-last-1);//不是倍数的话还要计算
            }
            StringBuilder str = new StringBuilder();
            for(int j=last;j<i;j++){
                str.append(words[j]);
                if(j<i-1){//words[i-1]的话后面就不用填空格了，所以这里j<i-1
                    for(int k=0;k<spaceNum;k++)
                        str.append(" ");
                    
                    if(extraNum>0)
                        str.append(" ");
                    
                    extraNum--;
                }
            }
            
            //下面这个for循环作用于一行只有一个单词还没填满一行的情况
            for(int j=str.length();j<L;j++)
                str.append(" ");
                
            res.add(str.toString());
            count=0;
            last=i;//下一个开始的单词
        }
        count += words[i].length();
    }
    
    //处理最后一行
    StringBuilder str = new StringBuilder();
    for(int i=last;i<words.length;i++){
        str.append(words[i]);
        if(str.length()<L)
            str.append(" ");
    }
    for(int i=str.length();i<L;i++)
        str.append(" ");
    
    res.add(str.toString());
    return res;
}