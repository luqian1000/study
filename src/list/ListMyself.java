package list;

public interface ListMyself<E>  {



    /**
     * 长度
     * @return
     */
    int size();

    /**
     * 新增
     * @param element
     * @return 是否新增成功
     */
    boolean add(E element);

    /**
     * 移除某个位置的元素
     * @param index 位置
     * @return 移除的元素值
     */
    E remove(int index);

    /**
     * 移除特定元素
     * @param e 要移除的元素
     * @return 是否移除成功
     */
    boolean remove(E e);

    /**
     * 获取特定位置元素
     * @param index
     * @return 特定位置元素值
     */
    E get(int index);

    /**
     * 获取特定元素位置
     * @param element
     * @return 所在的位置，没有返回-1
     */
    int indexOf(E element);

    /**
     * 替换某个位置的元素
     * @param index 要替换的元素的位置
     * @param element 要替换的新值
     * @return 被替换的元素的值
     */
    E set(int index, E element);

    /**
     * 在某个位置新增元素
     * @param index 要新增元素的位置
     * @param element 要新增的元素
     */
    void add(int index, E element);
}
