package BloomFilter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.BitSet;    

public class BloomFilter {    
	    private int defaultSize = 5000 << 10000;   
	    private int basic = defaultSize -1;   
	    private String key = null;   
	    private BitSet bits = new BitSet(defaultSize);   
	    
	    public BitSet getBits() {
			return bits;
		}

		public void setBits(BitSet bits) {
			this.bits = bits;
		}

		public BloomFilter(){
	    	
	    }
	       
	    public BloomFilter(String key){   
	        this.key = key;   
	    }   
	       
	    private int[] lrandom(String key){   
	        int[] randomsum = new int[8];   
	        int random1 = hashCode(key,1);   
	        int random2 = hashCode(key,2);   
	        int random3 = hashCode(key,3);   
	        int random4 = hashCode(key,4);   
	        int random5 = hashCode(key,5);   
	        int random6 = hashCode(key,6);   
	        int random7 = hashCode(key,7);   
	        int random8 = hashCode(key,8);   
	        randomsum[0] = random1;   
	        randomsum[1] = random2;   
	        randomsum[2] = random3;   
	        randomsum[3] = random4;   
	        randomsum[4] = random5;   
	        randomsum[5] = random6;   
	        randomsum[6] = random7;   
	        randomsum[7] = random8;   
	        return randomsum;   
	    }   
	       
	 /*   private int[] sameLrandom(){   
	        int[] randomsum = new int[8];   
	        int random1 = hashCode(key,1);   
	        int random2 = hashCode(key,1);   
	        int random3 = hashCode(key,1);   
	        int random4 = hashCode(key,1);   
	        int random5 = hashCode(key,1);   
	        int random6 = hashCode(key,1);   
	        int random7 = hashCode(key,1);   
	        int random8 = hashCode(key,1);   
	        randomsum[0] = random1;   
	        randomsum[1] = random2;   
	        randomsum[2] = random3;   
	        randomsum[3] = random4;   
	        randomsum[4] = random5;   
	        randomsum[5] = random6;   
	        randomsum[6] = random7;   
	        randomsum[7] = random8;   
	        return randomsum;   
	    }   */
	       
	    private void add(String key){   
	        if(exist( key)){   
	            System.out.println("已经包含("+key+")");   
	            return;   
	        }   
	        int keyCode[] = lrandom(key);   
	        bits.set(keyCode[0]);   
	        bits.set(keyCode[1]);   
	        bits.set(keyCode[2]);    
	        bits.set(keyCode[3]);    
	        bits.set(keyCode[4]);    
	        bits.set(keyCode[5]);    
	        bits.set(keyCode[6]);    
	        bits.set(keyCode[7]);   
	    }   
	       
	    private boolean exist(String key){   
	        int keyCode[] = lrandom(key);   
	        if(bits.get(keyCode[0])&&   
	                bits.get(keyCode[1])   
	                &&bits.get(keyCode[2])   
	                &&bits.get(keyCode[3])   
	                &&bits.get(keyCode[4])   
	                &&bits.get(keyCode[5])   
	                &&bits.get(keyCode[6])   
	                &&bits.get(keyCode[7])){   
	            return true;    
	        }   
	        return false;   
	    }   
	       
//	    private boolean set0(){   
//	        if(exist()){   
//	            int keyCode[] = lrandom();   
//	            bits.clear(keyCode[0]);   
//	            bits.clear(keyCode[1]);   
//	            bits.clear(keyCode[2]);   
//	            bits.clear(keyCode[3]);   
//	            bits.clear(keyCode[4]);   
//	            bits.clear(keyCode[5]);   
//	            bits.clear(keyCode[6]);   
//	            bits.clear(keyCode[7]);   
//	            return true;   
//	        }   
//	        return false;   
//	    }   
	       
	    private int hashCode(String key,int Q){   
	        int h = 0;   
	        int off = 0;   
	        char val[] = key.toCharArray();   
	        int len = key.length();   
	        for (int i = 0; i < len; i++) {   
	            h = (30 + Q) * h + val[off++];   
	        }   
	        return changeInteger(h);   
	    }   
	       
	    private int changeInteger(int h) {   
	        return basic & h;   
	    }   
	       
	    public void saveBit(String filename){
	    	
	    	try {
	    		File file=new File(filename);
				ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(file,false));
				oos.writeObject(bits);
				oos.flush();
				oos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    
	    public BitSet readBit(String filename){
	    	BitSet bits=new BitSet(defaultSize);
	       File file=new File(filename);
	       if(!file.exists()){
	    	   return bits;
	       }
	       try {
			ObjectInputStream ois=new ObjectInputStream(new FileInputStream(file));
			 bits=(BitSet)ois.readObject();
			ois.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return bits;
	    }
	    public static void main(String[] args) {   
	    	
	    	String fileName="c:\\test\\BloomFilter.txt";
	    	String url="http://www.agrssdddd.com/";
	    	BloomFilter bf=new BloomFilter();
	    	BitSet bitSet=bf.readBit(fileName);
	    	bf.setBits(bitSet);
	    	bf.add(url);
	        System.out.println(bf.exist(url));
	        bf.saveBit(fileName);
	    	//System.out.println(5000 << 10000);
	    
	    	
	      /*  BloomFilter f = new BloomFilter("http://www.agrilink.cn/");   
	        f.add();   
	        System.out.println(f.exist());   */
//	        f.set0();   
//	        System.out.println(f.exist());   
	    }   

}   

