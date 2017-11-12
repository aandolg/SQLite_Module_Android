package in.good_work.sqlite_module.common;

/**
 * Created by Alex on 06.11.2017.
 */

public interface ItemClickListener<M> {
    void onItemClicked(M item, boolean b);
    void deleteItem(long itemId);
    void updateItem(long itemId);
    void openItemDetails(long itemId);
}
