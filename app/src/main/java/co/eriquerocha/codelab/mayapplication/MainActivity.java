package co.eriquerocha.codelab.mayapplication;

import static androidx.core.os.LocaleListCompat.create;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import co.tiagoaguiar.codelab.myapplication.R;

public class MainActivity extends AppCompatActivity {
	//private View btnImc;
	private RecyclerView rvMain;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	//antes de usar o recyclerView
	//btnImc = findViewById(R.id.btn_imc);
	//btnImc.setOnClickListener(view -> {
	//	Intent intent = new Intent(MainActivity.this, ImcActivity.class);
	//	startActivity(intent);
	//});

		rvMain = findViewById(R.id.main_rv);

		List<MainItem> mainItems = new ArrayList<>();
		mainItems.add(new MainItem(1, R.drawable.ic_baseline_accessibility_24, R.string.label_imc, Color.GREEN));
		mainItems.add(new MainItem(2, R.drawable.ic_baseline_self_improvement_24, R.string.label_tmb, Color.GRAY));

		rvMain.setLayoutManager(new GridLayoutManager(this, 2));

		MainAdapter adapter = new MainAdapter(mainItems);

		adapter.setListener(id -> {
			switch (id) {
				case 1:
				startActivity(new Intent(MainActivity.this, ImcActivity.class));
				break;
				case 2:
					AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
							.setTitle(getString(R.string.title_genero))
							.setMessage(getString(R.string.genero))
							.setPositiveButton(R.string.sim, (dialog1, which) -> {


								startActivity(new Intent(MainActivity.this, TmbActivity.class));

							})
							.setNegativeButton(R.string.não, (dialog1, which) -> {

							})
							.create();
					dialog.show();
				break;
			}

		});
		rvMain.setAdapter(adapter);

	}

	private class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder>{

		private List<MainItem> mainItems;
		private OnItemClickLstener listener;

		public MainAdapter(List<MainItem> mainItems){
			this.mainItems = mainItems;
		}

		public void setListener(OnItemClickLstener listener) {
			this.listener = listener;
		}

		@NonNull
		@Override
		public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

			return new MainViewHolder(getLayoutInflater().inflate(R.layout.main_item, parent, false));
		}

		@Override
		public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
			MainItem mainItemCurrent = mainItems.get(position);
			holder.bind(mainItemCurrent);
		}

		@Override
		public int getItemCount() {
			return mainItems.size();
		}
		private class MainViewHolder extends RecyclerView.ViewHolder{

			public MainViewHolder(@NonNull View itemView) {
				super(itemView);
			}
			public void bind(MainItem item){
				TextView textName = itemView.findViewById(R.id.item_txt_name);
				ImageView imgIcon = itemView.findViewById(R.id.item_img_icon);
				LinearLayout btnImc = (LinearLayout) itemView.findViewById(R.id.btn_imc);

				btnImc.setOnClickListener(view -> {
					listener.onClick(item.getId());
				});

				textName.setText(item.getTextStringId());
				imgIcon.setImageResource(item.getDrawableId());
				btnImc.setBackgroundColor(item.getColor());

			}
		}
	}


}