//package cn.grasscloud.jiaxiao.widgets;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.ViewGroup.LayoutParams;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.BaseAdapter;
//import android.widget.ListView;
//import android.widget.PopupWindow;
//import android.widget.TextView;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//import cn.grasscloud.jiaxiao.R;
//
//public class SpinerPopWindow extends PopupWindow implements OnItemClickListener {
//    private Context mContext;
//    private ListView mListView;
//    private AbstractSpinerAdapter mAdapter;
//    private AbstractSpinerAdapter.IOnItemSelectListener mItemSelectListener;
//
//    public SpinerPopWindow(Context context) {
//        super(context);
//
//        mContext = context;
//        init();
//    }
//
////    public void setItemListener(AbstractSpinerAdapter.IOnItemSelectListener listener) {
////        mItemSelectListener = listener;
////    }
//
////    public void setAdatper(AbstractSpinerAdapter adapter) {
////        mAdapter = adapter;
////        mListView.setAdapter(mAdapter);
////    }
//
//    private void init() {
//        View view = LayoutInflater.from(mContext).inflate(
//                R.layout.view_ablum_menu_top, null);
//        setContentView(view);
//        setWidth(LayoutParams.WRAP_CONTENT);
//        setHeight(LayoutParams.WRAP_CONTENT);
//
//        setFocusable(false);
//        setOutsideTouchable(false);
//
////        ColorDrawable dw = new ColorDrawable(0x00);
////        setBackgroundDrawable(dw);
//
//    mListView=(ListView)view.findViewById(R.id.list_menu);
//    mListView.setOnItemClickListener(this);
//}
//
//    public <T> void refreshData(List<T> list, int selIndex) {
//        if (list != null && selIndex != -1) {
//            if (mAdapter != null) {
//                mAdapter.refreshData(list, selIndex);
//            }
//        }
//    }
//
//    @Override
//    public void onItemClick(AdapterView<?> arg0, View view, int pos, long arg3) {
//        dismiss();
//        if (mItemSelectListener != null) {
//            mItemSelectListener.onItemClick(pos);
//        }
//    }
//
//    public static class AbstractSpinerAdapter<T> extends BaseAdapter {
//
//    public interface IOnItemSelectListener {
//        public void onItemClick(int pos);
//    }
//
//    private Context mContext;
//    private List<T> mObjects = new ArrayList<T>();
//    private int mSelectItem = 0;
//
//    private LayoutInflater mInflater;
//
//    public AbstractSpinerAdapter(Context context) {
//        init(context);
//    }
//
//    public void refreshData(List<T> objects, int selIndex) {
//        mObjects = objects;
//        if (selIndex < 0) {
//            selIndex = 0;
//        }
//        if (selIndex >= mObjects.size()) {
//            selIndex = mObjects.size() - 1;
//        }
//
//        mSelectItem = selIndex;
//    }
//
//    private void init(Context context) {
//        mContext = context;
//        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }
//
//
//    @Override
//    public int getCount() {
//
//        return mObjects.size();
//    }
//
//    @Override
//    public Object getItem(int pos) {
//        return mObjects.get(pos).toString();
//    }
//
//    @Override
//    public long getItemId(int pos) {
//        return pos;
//    }
//
//    @Override
//    public View getView(int pos, View convertView, ViewGroup arg2) {
//        ViewHolder viewHolder;
//
//        if (convertView == null) {
//            convertView = mInflater.inflate(R.layout.view_pop_menu_textview, null);
//            viewHolder = new ViewHolder();
//            viewHolder.mTextView = (TextView) convertView.findViewById(R.id.menu_name);
//            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//
//
//        Object item = getItem(pos);
//        viewHolder.mTextView.setText(item.toString());
//
//        return convertView;
//    }
//
//    class ViewHolder {
//        public TextView mTextView;
//    }
//
//}
//}
