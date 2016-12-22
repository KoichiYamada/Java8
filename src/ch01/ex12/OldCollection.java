package ch01.ex12;

import java.util.ArrayList;
import java.util.Collection;

public class OldCollection<E> extends ArrayList<E> implements Collection<E> {
    // 本クラスをJava7プロジェクトに追加し、以下のコメントを外してコンパイルし、jarを作ります
    // public String stream() {
    // return "stream";
    // }
}
