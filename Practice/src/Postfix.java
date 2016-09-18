import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/*************************************************************************
 * > Project Name: Practice
 * > Author: james
 * > Mail: caterpillarous@gmail.com
 * > Created Time: 2016 09 17 15:34
 ************************************************************************/
class Parser {
    private static int lookahead;
    private static int count = 0;
    private static int sum = 0;
    private static byte[] b;
    public Parser() throws IOException{
        File file = new File("F:\\class\\Calculator\\Practice\\src\\test.txt");
        InputStream input = new FileInputStream(file) ;
        b = new byte[(int)file.length()] ;
        sum = input.read(b) ;
        lookahead = b[0];
        //System.out.println(count);
        //lookahead = System.in.read(b);
        //System.out.println(Arrays.toString(b));
    }
    private void getNext(){
        if (count < sum - 1){
            lookahead = b[++count];
        }
    }

    /**
     * 如果是符合则读入下一个字符 下一个字符如果是数字则输出
     * @throws IOException
     */
    void expr() throws IOException{
        term();
        while(true){
            if (lookahead == '+'){
                match('+'); term(); System.out.println('+');
            }else if (lookahead == '-'){
                match('-'); term();System.out.println('-');
            }else return;
        }
    }

    /**
     * 如果是数字则直接输出并且读入下一个字符
     * @throws IOException
     */
    private void term() throws IOException{
        if (Character.isDigit((char)lookahead)){
            System.out.println((char)lookahead);match(lookahead);
        }
        else throw new Error("syntax error");
    }
    private void match(int t) throws IOException{
        if (lookahead == t){
            //lookahead = System.in.read();
            getNext();
        }else throw new Error("syntax error");
    }
}
public class Postfix{
    public static void main(String[] args) throws IOException {
        Parser parser = new Parser();
        parser.expr();
        System.out.println('\n');
    }
}
