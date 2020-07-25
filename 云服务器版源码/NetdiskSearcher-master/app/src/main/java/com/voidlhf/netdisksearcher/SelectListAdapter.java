package com.voidlhf.netdisksearcher;
import android.widget.*;
import android.content.*;
import java.util.*;
import android.view.*;

public class SelectListAdapter extends BaseAdapter
{
	private Context mContext;  
    private List<SelectListItem> mDatas1;
	private List<SelectListItem> mDatas2 = new ArrayList<SelectListItem>(); 

	public SelectListAdapter(Context context,List<SelectListItem> mDatas) {
        this.mContext = context;  
        this.mDatas1 = mDatas;
		this.mDatas2.addAll(this.mDatas1);
	}

	@Override
	public int getCount()
	{
		// TODO: Implement this method
		//return mDatas.size();
		return mDatas2 == null ? 0 : mDatas2.size();
	}

	@Override
	public Object getItem(int p1)
	{
		// TODO: Implement this method
		return mDatas2.get(p1);
	}

	@Override
	public long getItemId(int p1)
	{
		// TODO: Implement this method
		return p1;
	}

	@Override
	public void notifyDataSetChanged()
	{
		// TODO: Implement this method
		this.mDatas2.clear();
		this.mDatas2.addAll(mDatas1);
		super.notifyDataSetChanged();
	}


	@Override
	public View getView(int p1, View p2, ViewGroup p3)
	{
		SelectListItem item = (SelectListItem) getItem(p1);
		View view;
		ViewHolder viewHolder;
		if(p2==null) {
			view = LayoutInflater.from(mContext).
				inflate(R.layout.select_list_item,p3,false);
			viewHolder = new ViewHolder();
			viewHolder.textView1 = view.findViewById(R.id.selectlistitemTextView1);
			//viewHolder.textView2 = view.findViewById(R.id.selectlistitemTextView2);
			viewHolder.imageView = view.findViewById(R.id.selectImg);
			view.setTag(viewHolder);
		} else {
			view = p2;
			viewHolder = (ViewHolder) view.getTag();
		}
		viewHolder.textView1.setText(item.getItemName());
		//viewHolder.textView2.setText(item.getItemDescription());
		if(item.isItemSelected()) {
			viewHolder.imageView.setVisibility(View.VISIBLE);
		} else {
			viewHolder.imageView.setVisibility(View.GONE);
		}
		return view;
	}

	class ViewHolder {
		TextView textView1;
		//TextView textView2;
		ImageView imageView;
	}
}
