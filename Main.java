import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main1(String[] args) {
        //  给一个类似下面的字符串，字符串里有一些双括号括起的内容，
        // 并且给定一个字典，我们需要编写一段程序将括号以及括号里的内容替换为字典里映射的内容。
        // 1. 如果字典里不存在需要的映射，则输出只是将括号去掉，括号里的内容原样保存。
        // 2. 如果括号里的内容为空，则括号原样输出。
        // 3. 匹配的时候以最短优先原则
        // 比如有{{abcde{{fg}}，则取fg，而不取 abcde{{fg；
        // 比如{{a{{}}c}}则取中间的{{}}，则替换后结果还是{{a{{}}c}}。
        // 4. 替换只会从左到右执行一遍。
        // 比如有{{abc}}, 字典 abc -> a{{b}}c，b -> c，则结果是a{{b}}c，不会对b再进行一次替换。

        // 输入描述
        // String parse(String template,int count, Map<String, String> dict)
        // template: 要被替换的模板
        // count:字典个数
        // dict: 字典内容
        // 输出描述
        // 替换后的内容

        // 样例输入
        // 这是{{unit}}很长很长的句子。
        // 1
        // unit->一个
        // 样例输出
        // 这是一个很长很长的句子。

        // 提示
        // 输入:
        // 这是{{unit}}很长很长的句子。
        // 0
        // 输出:
        // 这是unit很长很长的句子。

        // 输入:
        // 这是{{}}很长很长的句子。
        // 0
        // 输出:
        // 这是{{}}很长很长的句子。

        // 输入:
        // 这是{{}}很长很长的句子。
        // 0
        // 输出:
        // 这是{{}}很长很长的句子。

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String template = sc.nextLine();
            char[] chars = template.toCharArray();
            int count = sc.nextInt();
            String dict = sc.next();
            char[] ch = dict.toCharArray();
            ArrayList<ArrayList<Character>> listAll = new ArrayList<>();
            ArrayList<Character> list1 = new ArrayList<>();
            ArrayList<Character> list2 = new ArrayList<>();
            ArrayList<Character> list3 = new ArrayList<>();
            int cur = 0;
            while (chars[cur] != '{'){
                list1.add(chars[cur]);
                cur++;
            }
            listAll.add(list1);
            while (chars[cur] != '}') {
                list2.add(chars[cur]);
                cur++;
            }
            list2.add('}');
            list2.add('}');
            listAll.add(list2);
            for (cur += 2; cur < chars.length; cur++) {
                if (chars[cur] != '}') {
                    list3.add(chars[cur]);
                }
            }
            if (count == 0 && list2.size() <= 4) {
                listAll.add(list2);
            } else if (count == 0 && list2.size() > 4){
                ArrayList<Character> list = new ArrayList<>();
                for (char l : list2) {
                    if (l != '{' && l != '}') {
                        list.add(l);
                    }
                }
                listAll.add(list);
            } else {
                ArrayList<Character> list = new ArrayList<>();
                int add = 0;
                for (char c : ch) {
                    add++;
                    if (ch[add] == '>') {
                        for (add += 1; add < ch.length; add++) {
                            list.add(ch[add]);
                        }
                        break;
                    }
                }
                listAll.add(list);
            }
            listAll.add(list3);
            for (ArrayList<Character> list : listAll) {
                for (char l : list) {
                    if (l != '[' && l != ']' && l != ',') {
                        System.out.println(l);
                    }
                }
            }
        }
    }

    public static void main2(String[] args) {
        // 给定一个正整数数n, 一个正整数k 一个可行的数字组合是一组正整数
        // 它们的和为n，且组合中的数字个数小于等于k, 组合中允许数字重复出现
        // 输出：所有可能的组合数 (两个组合中出现的数字相同，顺序不同计为同一个）

        // 输入描述
        // public int numOf (int n, int k )
        // 说明：
        // n：组合累加和
        // k：最多可以使用的正整数个数
        // 输出描述
        // 返回：组合数

        // 样例输入
        // 5
        // 2
        // 样例输出
        // 3

        // 提示
        // 5=5
        // 5=1+4
        // 5=2+3

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            int sum = 0;
            int count = 0;
            for (int i = 0; i < n; i++) {
                sum = 0;
                int temp = i;
                for (int j = 0; j < k; j++) {
                    sum += temp;
                    temp++;
                    if (sum > n) {
                        break;
                    }
                    if (sum == n) {
                        count++;
                    }
                }
            }
            System.out.println(count);
        }
    }

    public static void main(String[] args) {
        // 便利蜂手机 APP 由多个模块构成。
        // 模块更新时，可以指定依赖关系。若
        // A 模块依赖 B 模块，则 B 模块更新后，A 模块才可以更新；如 A、B 模块相互依赖 ，则 A、B 需要同时更新。

        // 求：计算某个模块的更新，需要依赖的其他模块数量。
        // 如：给定模块依赖关系 A->B , B->C，则对于模块 A，依赖的模块数量为 2。

        // 输入描述
        // 第1行：需要查询依赖的模块
        // 第2行：依赖关系行数
        // 剩余行：依赖关系列表
        // 输出描述
        // 返回：依赖的模块数量

        // 样例输入
        // A
        // 3
        // A->B
        // B->C
        // C->A
        // 样例输出
        // 2
        
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String part = sc.next();
            int line = sc.nextInt();
            int count = 1;
            Map<String, Integer> map = new HashMap<>();
            for (int i = 0; i < line; i++) {
                String str = sc.nextLine();
                if (!map.containsKey(str)) {
                    map.put(str, 1);
                } else {
                    map.put(str, count);
                }
            }
        }
    }
}
