package utils;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by Administrator on 2018/3/31.
 */
public class NgWordUtil {
    private static final Map keysMap = new HashMap();
    private static int matchType = 1;
    private static NgWordUtil instance = new NgWordUtil();

    private NgWordUtil() {
        this.initfiltercode();
    }

    public static NgWordUtil getInstance() {
        return instance;
    }

    private void addKeywords(Set<String> keywords) {
        Iterator iterator = keywords.iterator();

        while(iterator.hasNext()) {
            String key = ((String)iterator.next()).trim();
            Object nowhash = null;
            nowhash = keysMap;

            for(int j = 0; j < key.length(); ++j) {
                char word = key.charAt(j);
                Object wordMap = ((Map)nowhash).get(Character.valueOf(word));
                if(wordMap != null) {
                    nowhash = (HashMap)wordMap;
                } else {
                    HashMap newWordHash = new HashMap();
                    newWordHash.put("isEnd", "0");
                    ((Map)nowhash).put(Character.valueOf(word), newWordHash);
                    nowhash = newWordHash;
                }

                if(j == key.length() - 1) {
                    ((Map)nowhash).put("isEnd", "1");
                }
            }
        }

    }

    private void clearKeywords() {
        keysMap.clear();
    }

    private int checkKeyWords(String txt, int begin, int flag) {
        Map nowhash = null;
        nowhash = keysMap;
        int maxMatchRes = 0;
        int res = 0;
        int l = txt.length();
        boolean word = false;

        for(int i = begin; i < l; ++i) {
            char var11 = txt.charAt(i);
            Object wordMap = nowhash.get(Character.valueOf(var11));
            if(wordMap == null) {
                txt = null;
                nowhash = null;
                return maxMatchRes;
            }

            ++res;
            nowhash = (Map)wordMap;
            if(((String)nowhash.get("isEnd")).equals("1")) {
                if(flag == 1) {
                    wordMap = null;
                    nowhash = null;
                    txt = null;
                    return res;
                }

                maxMatchRes = res;
            }
        }

        txt = null;
        nowhash = null;
        return maxMatchRes;
    }

    public List<String> getTxtKeyWords(String txt) {
        ArrayList list = new ArrayList();
        int l = txt.length();
        int i = 0;

        while(i < l) {
            int len = this.checkKeyWords(txt, i, matchType);
            if(len > 0) {
                String tt = "<font color=\'#ff0000\'>" + txt.substring(i, i + len) + "</font>";
                list.add(tt);
                i += len;
            } else {
                ++i;
            }
        }

        txt = null;
        return list;
    }

    public String doFilter(String txt) {
        StringBuffer sb = new StringBuffer(txt);
        int l = txt.length();
        int i = 0;

        while(i < l) {
            int len = this.checkKeyWords(txt, i, matchType);
            if(len > 0) {
                sb.replace(i, i + len, StringUtils.repeat('*', len));
                i += len;
            } else {
                ++i;
            }
        }

        txt = null;
        return sb.toString();
    }

    public boolean isContentKeyWords(String txt) {
        for(int i = 0; i < txt.length(); ++i) {
            int len = this.checkKeyWords(txt, i, 1);
            if(len > 0) {
                return true;
            }
        }

        txt = null;
        return false;
    }

    private void initfiltercode() {
        HashSet keywords = new HashSet();
        InputStream in = NgWordUtil.class.getClassLoader().getResourceAsStream("words.properties");

        try {
            InputStreamReader e = new InputStreamReader(in, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(e);
            String txt = null;

            while((txt = bufferedReader.readLine()) != null) {
                keywords.add(txt);
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        this.addKeywords(keywords);
    }

    private int getMatchType() {
        return matchType;
    }

    private void setMatchType(int matchType) {
        matchType = matchType;
    }

    public static void main(String[] args) throws IOException {
        String txt = "百家乐她长孙家提携恩情，FL大法不会有人去追查这件事的，中南海权力斗争就一次，就一次，好吗？SM用品";
        boolean boo = getInstance().isContentKeyWords("百家乐她长孙家提携恩情，FL大法不会有人去追查这件事的，中南海权力斗争就一次，就一次，好吗？SM用品");
        System.out.println(boo);
        List set = getInstance().getTxtKeyWords("百家乐她长孙家提携恩情，FL大法不会有人去追查这件事的，中南海权力斗争就一次，就一次，好吗？SM用品");
        System.out.println("包含的敏感词如下：" + set);
        String ret = getInstance().doFilter("百家乐她长孙家提携恩情，FL大法不会有人去追查这件事的，中南海权力斗争就一次，就一次，好吗？SM用品");
        System.out.println("替换后：" + ret);
    }
}
