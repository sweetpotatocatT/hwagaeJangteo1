package www.hwagae.com.hwagae;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BoardAdapter extends BaseAdapter {
    private Context mContext = null;

    private ArrayList<WriteData> mWriteData = new ArrayList<>();

    public BoardAdapter(Context mContext, ArrayList<WriteData> list) {
//        super();
        this.mContext = mContext;
        mWriteData = list;
    }

    @Override
    public int getCount() {
        return mWriteData.size();
    }

    @Override
    public Object getItem(int i) {
        return mWriteData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.board_item, null);
//            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            view = inflater.inflate(R.layout.board_item, null);
        }
        TextView tv_board_title = (TextView) view.findViewById(R.id.tv_board_title);
        TextView tv_board_price = (TextView) view.findViewById(R.id.tv_board_price);

        tv_board_title.setText(mWriteData.get(i).getTitle());
        tv_board_price.setText(mWriteData.get(i).getPrice());

        return view;
    }

    public void add(String title, String price) {
        WriteData writeData = new WriteData();

        writeData.setTitle(title);
        writeData.setPrice(price);
        writeData.setContent("temp");

        mWriteData.add(writeData);
    }
}
