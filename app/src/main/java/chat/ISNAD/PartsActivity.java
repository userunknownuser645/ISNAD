package Chat.ISNAD;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import java.util.ArrayList;
import java.util.HashMap;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import de.hdodenhof.circleimageview.*;
import android.widget.TextView;
import android.widget.ImageView;
import android.app.Activity;
import android.content.SharedPreferences;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import android.os.Bundle;
import java.io.InputStream;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import com.bumptech.glide.Glide;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class PartsActivity extends AppCompatActivity {
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private ArrayList<HashMap<String, Object>> lm1 = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> lm2 = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> lm3 = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> lm4 = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> lm5 = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> lm6 = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> lm7 = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> lm8 = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> lm9 = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> lm10 = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> lm11 = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear5;
	private ScrollView vscroll1;
	private CircleImageView circleimageview1;
	private TextView textview2;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private LinearLayout linear9;
	private LinearLayout linear15;
	private LinearLayout linear14;
	private LinearLayout linear13;
	private LinearLayout linear12;
	private LinearLayout linear11;
	private LinearLayout linear10;
	private LinearLayout linear8;
	private ImageView imageview13;
	private LinearLayout linear16;
	private TextView textview1;
	private ImageView imageview1;
	private ImageView imageview14;
	private LinearLayout linear17;
	private TextView textview3;
	private ImageView imageview3;
	private ImageView imageview15;
	private LinearLayout linear18;
	private TextView textview4;
	private ImageView imageview4;
	private ImageView imageview16;
	private LinearLayout linear19;
	private TextView textview6;
	private ImageView imageview6;
	private ImageView imageview17;
	private LinearLayout linear20;
	private TextView textview12;
	private ImageView imageview12;
	private ImageView imageview18;
	private LinearLayout linear21;
	private TextView textview11;
	private ImageView imageview11;
	private ImageView imageview19;
	private LinearLayout linear22;
	private TextView textview10;
	private ImageView imageview10;
	private ImageView imageview20;
	private LinearLayout linear23;
	private TextView textview9;
	private ImageView imageview9;
	private ImageView imageview21;
	private LinearLayout linear24;
	private TextView textview8;
	private ImageView imageview8;
	private ImageView imageview23;
	private LinearLayout linear25;
	private TextView textview7;
	private ImageView imageview7;
	private ImageView imageview24;
	private LinearLayout linear26;
	private TextView textview5;
	private ImageView imageview5;
	
	private SharedPreferences login;
	private SharedPreferences parts;
	private DatabaseReference msg = _firebase.getReference("msg");
	private ChildEventListener _msg_child_listener;
	private Intent i = new Intent();
	private DatabaseReference msg2 = _firebase.getReference("msg2");
	private ChildEventListener _msg2_child_listener;
	private DatabaseReference msg3 = _firebase.getReference("msg3");
	private ChildEventListener _msg3_child_listener;
	private DatabaseReference msg4 = _firebase.getReference("msg4");
	private ChildEventListener _msg4_child_listener;
	private DatabaseReference msg5 = _firebase.getReference("msg5");
	private ChildEventListener _msg5_child_listener;
	private DatabaseReference msg6 = _firebase.getReference("msg6");
	private ChildEventListener _msg6_child_listener;
	private DatabaseReference msg7 = _firebase.getReference("msg7");
	private ChildEventListener _msg7_child_listener;
	private DatabaseReference msg8 = _firebase.getReference("msg8");
	private ChildEventListener _msg8_child_listener;
	private DatabaseReference msg9 = _firebase.getReference("msg9");
	private ChildEventListener _msg9_child_listener;
	private DatabaseReference msg10 = _firebase.getReference("msg10");
	private ChildEventListener _msg10_child_listener;
	private DatabaseReference msg11 = _firebase.getReference("msg11");
	private ChildEventListener _msg11_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.parts);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		vscroll1 = (ScrollView) findViewById(R.id.vscroll1);
		circleimageview1 = (CircleImageView) findViewById(R.id.circleimageview1);
		textview2 = (TextView) findViewById(R.id.textview2);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		linear9 = (LinearLayout) findViewById(R.id.linear9);
		linear15 = (LinearLayout) findViewById(R.id.linear15);
		linear14 = (LinearLayout) findViewById(R.id.linear14);
		linear13 = (LinearLayout) findViewById(R.id.linear13);
		linear12 = (LinearLayout) findViewById(R.id.linear12);
		linear11 = (LinearLayout) findViewById(R.id.linear11);
		linear10 = (LinearLayout) findViewById(R.id.linear10);
		linear8 = (LinearLayout) findViewById(R.id.linear8);
		imageview13 = (ImageView) findViewById(R.id.imageview13);
		linear16 = (LinearLayout) findViewById(R.id.linear16);
		textview1 = (TextView) findViewById(R.id.textview1);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		imageview14 = (ImageView) findViewById(R.id.imageview14);
		linear17 = (LinearLayout) findViewById(R.id.linear17);
		textview3 = (TextView) findViewById(R.id.textview3);
		imageview3 = (ImageView) findViewById(R.id.imageview3);
		imageview15 = (ImageView) findViewById(R.id.imageview15);
		linear18 = (LinearLayout) findViewById(R.id.linear18);
		textview4 = (TextView) findViewById(R.id.textview4);
		imageview4 = (ImageView) findViewById(R.id.imageview4);
		imageview16 = (ImageView) findViewById(R.id.imageview16);
		linear19 = (LinearLayout) findViewById(R.id.linear19);
		textview6 = (TextView) findViewById(R.id.textview6);
		imageview6 = (ImageView) findViewById(R.id.imageview6);
		imageview17 = (ImageView) findViewById(R.id.imageview17);
		linear20 = (LinearLayout) findViewById(R.id.linear20);
		textview12 = (TextView) findViewById(R.id.textview12);
		imageview12 = (ImageView) findViewById(R.id.imageview12);
		imageview18 = (ImageView) findViewById(R.id.imageview18);
		linear21 = (LinearLayout) findViewById(R.id.linear21);
		textview11 = (TextView) findViewById(R.id.textview11);
		imageview11 = (ImageView) findViewById(R.id.imageview11);
		imageview19 = (ImageView) findViewById(R.id.imageview19);
		linear22 = (LinearLayout) findViewById(R.id.linear22);
		textview10 = (TextView) findViewById(R.id.textview10);
		imageview10 = (ImageView) findViewById(R.id.imageview10);
		imageview20 = (ImageView) findViewById(R.id.imageview20);
		linear23 = (LinearLayout) findViewById(R.id.linear23);
		textview9 = (TextView) findViewById(R.id.textview9);
		imageview9 = (ImageView) findViewById(R.id.imageview9);
		imageview21 = (ImageView) findViewById(R.id.imageview21);
		linear24 = (LinearLayout) findViewById(R.id.linear24);
		textview8 = (TextView) findViewById(R.id.textview8);
		imageview8 = (ImageView) findViewById(R.id.imageview8);
		imageview23 = (ImageView) findViewById(R.id.imageview23);
		linear25 = (LinearLayout) findViewById(R.id.linear25);
		textview7 = (TextView) findViewById(R.id.textview7);
		imageview7 = (ImageView) findViewById(R.id.imageview7);
		imageview24 = (ImageView) findViewById(R.id.imageview24);
		linear26 = (LinearLayout) findViewById(R.id.linear26);
		textview5 = (TextView) findViewById(R.id.textview5);
		imageview5 = (ImageView) findViewById(R.id.imageview5);
		login = getSharedPreferences("login", Activity.MODE_PRIVATE);
		parts = getSharedPreferences("parts", Activity.MODE_PRIVATE);
		
		linear4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				parts.edit().putString("1", String.valueOf((long)(lm1.size()))).commit();
				i.setClass(getApplicationContext(), HomeActivity.class);
				i.putExtra("name", login.getString("name", ""));
				i.putExtra("img", login.getString("img", ""));
				i.putExtra("part", "1");
				startActivity(i);
				finish();
			}
		});
		
		linear6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				parts.edit().putString("2", String.valueOf((long)(lm2.size()))).commit();
				i.setClass(getApplicationContext(), HomeActivity.class);
				i.putExtra("name", login.getString("name", ""));
				i.putExtra("img", login.getString("img", ""));
				i.putExtra("part", "2");
				startActivity(i);
				finish();
			}
		});
		
		linear7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				parts.edit().putString("3", String.valueOf((long)(lm3.size()))).commit();
				i.setClass(getApplicationContext(), HomeActivity.class);
				i.putExtra("name", login.getString("name", ""));
				i.putExtra("img", login.getString("img", ""));
				i.putExtra("part", "3");
				startActivity(i);
				finish();
			}
		});
		
		linear9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				parts.edit().putString("4", String.valueOf((long)(lm4.size()))).commit();
				i.setClass(getApplicationContext(), HomeActivity.class);
				i.putExtra("name", login.getString("name", ""));
				i.putExtra("img", login.getString("img", ""));
				i.putExtra("part", "4");
				startActivity(i);
				finish();
			}
		});
		
		linear15.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				parts.edit().putString("5", String.valueOf((long)(lm5.size()))).commit();
				i.setClass(getApplicationContext(), HomeActivity.class);
				i.putExtra("name", login.getString("name", ""));
				i.putExtra("img", login.getString("img", ""));
				i.putExtra("part", "5");
				startActivity(i);
				finish();
			}
		});
		
		linear14.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				parts.edit().putString("6", String.valueOf((long)(lm6.size()))).commit();
				i.setClass(getApplicationContext(), HomeActivity.class);
				i.putExtra("name", login.getString("name", ""));
				i.putExtra("img", login.getString("img", ""));
				i.putExtra("part", "6");
				startActivity(i);
				finish();
			}
		});
		
		linear13.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				parts.edit().putString("7", String.valueOf((long)(lm7.size()))).commit();
				i.setClass(getApplicationContext(), HomeActivity.class);
				i.putExtra("name", login.getString("name", ""));
				i.putExtra("img", login.getString("img", ""));
				i.putExtra("part", "7");
				startActivity(i);
				finish();
			}
		});
		
		linear12.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				parts.edit().putString("8", String.valueOf((long)(lm8.size()))).commit();
				i.setClass(getApplicationContext(), HomeActivity.class);
				i.putExtra("name", login.getString("name", ""));
				i.putExtra("img", login.getString("img", ""));
				i.putExtra("part", "8");
				startActivity(i);
				finish();
			}
		});
		
		linear11.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				parts.edit().putString("9", String.valueOf((long)(lm9.size()))).commit();
				i.setClass(getApplicationContext(), HomeActivity.class);
				i.putExtra("name", login.getString("name", ""));
				i.putExtra("img", login.getString("img", ""));
				i.putExtra("part", "9");
				startActivity(i);
				finish();
			}
		});
		
		linear10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				parts.edit().putString("10", String.valueOf((long)(lm10.size()))).commit();
				i.setClass(getApplicationContext(), HomeActivity.class);
				i.putExtra("name", login.getString("name", ""));
				i.putExtra("img", login.getString("img", ""));
				i.putExtra("part", "10");
				startActivity(i);
				finish();
			}
		});
		
		linear8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				parts.edit().putString("11", String.valueOf((long)(lm11.size()))).commit();
				i.setClass(getApplicationContext(), HomeActivity.class);
				i.putExtra("name", login.getString("name", ""));
				i.putExtra("img", login.getString("img", ""));
				i.putExtra("part", "11");
				startActivity(i);
				finish();
			}
		});
		
		_msg_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		msg.addChildEventListener(_msg_child_listener);
		
		_msg2_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		msg2.addChildEventListener(_msg2_child_listener);
		
		_msg3_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		msg3.addChildEventListener(_msg3_child_listener);
		
		_msg4_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		msg4.addChildEventListener(_msg4_child_listener);
		
		_msg5_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		msg5.addChildEventListener(_msg5_child_listener);
		
		_msg6_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		msg6.addChildEventListener(_msg6_child_listener);
		
		_msg7_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		msg7.addChildEventListener(_msg7_child_listener);
		
		_msg8_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		msg8.addChildEventListener(_msg8_child_listener);
		
		_msg9_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		msg9.addChildEventListener(_msg9_child_listener);
		
		_msg10_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		msg10.addChildEventListener(_msg10_child_listener);
		
		_msg11_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		msg11.addChildEventListener(_msg11_child_listener);
	}
	
	private void initializeLogic() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { Window w = getWindow();  w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS); };
		if (login.getString("name", "").contains("🌟")) {
			linear5.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)35, (int)1, 0xFFFFC107, 0xFF37474F));
		}
		else {
			linear5.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)35, 0xFF37474F));
		}
		if (login.getString("img", "").length() > 0) {
			Glide.with(getApplicationContext()).load(Uri.parse(login.getString("img", ""))).into(circleimageview1);
		}
		textview2.setText(login.getString("name", ""));
		linear4.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)35, 0xFF37474F));
		linear6.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)35, 0xFF37474F));
		linear7.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)35, 0xFF37474F));
		linear8.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)35, 0xFF37474F));
		linear9.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)35, 0xFF37474F));
		linear10.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)35, 0xFF37474F));
		linear11.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)35, 0xFF37474F));
		linear12.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)35, 0xFF37474F));
		linear13.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)35, 0xFF37474F));
		linear14.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)35, 0xFF37474F));
		linear15.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)35, 0xFF37474F));
		imageview13.setColorFilter(0xFFF44336, PorterDuff.Mode.MULTIPLY);
		imageview14.setColorFilter(0xFFF44336, PorterDuff.Mode.MULTIPLY);
		imageview15.setColorFilter(0xFFF44336, PorterDuff.Mode.MULTIPLY);
		imageview16.setColorFilter(0xFFF44336, PorterDuff.Mode.MULTIPLY);
		imageview17.setColorFilter(0xFFF44336, PorterDuff.Mode.MULTIPLY);
		imageview18.setColorFilter(0xFFF44336, PorterDuff.Mode.MULTIPLY);
		imageview19.setColorFilter(0xFFF44336, PorterDuff.Mode.MULTIPLY);
		imageview20.setColorFilter(0xFFF44336, PorterDuff.Mode.MULTIPLY);
		imageview21.setColorFilter(0xFFF44336, PorterDuff.Mode.MULTIPLY);
		imageview23.setColorFilter(0xFFF44336, PorterDuff.Mode.MULTIPLY);
		imageview24.setColorFilter(0xFFF44336, PorterDuff.Mode.MULTIPLY);
		imageview13.setAlpha((float)(0));
		imageview14.setAlpha((float)(0));
		imageview15.setAlpha((float)(0));
		imageview16.setAlpha((float)(0));
		imageview17.setAlpha((float)(0));
		imageview18.setAlpha((float)(0));
		imageview19.setAlpha((float)(0));
		imageview20.setAlpha((float)(0));
		imageview21.setAlpha((float)(0));
		imageview23.setAlpha((float)(0));
		imageview24.setAlpha((float)(0));
		msg.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				lm1 = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						lm1.add(_map);
					}
				}
				catch (Exception _e) {
					_e.printStackTrace();
				}
				if (!parts.getString("1", "").equals(String.valueOf((long)(lm1.size())))) {
					imageview13.setAlpha((float)(1));
				}
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
		msg2.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				lm2 = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						lm2.add(_map);
					}
				}
				catch (Exception _e) {
					_e.printStackTrace();
				}
				if (!parts.getString("2", "").equals(String.valueOf((long)(lm2.size())))) {
					imageview14.setAlpha((float)(1));
				}
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
		msg3.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				lm3 = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						lm3.add(_map);
					}
				}
				catch (Exception _e) {
					_e.printStackTrace();
				}
				if (!parts.getString("3", "").equals(String.valueOf((long)(lm3.size())))) {
					imageview15.setAlpha((float)(1));
				}
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
		msg4.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				lm4 = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						lm4.add(_map);
					}
				}
				catch (Exception _e) {
					_e.printStackTrace();
				}
				if (!parts.getString("4", "").equals(String.valueOf((long)(lm4.size())))) {
					imageview16.setAlpha((float)(1));
				}
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
		msg5.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				lm5 = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						lm5.add(_map);
					}
				}
				catch (Exception _e) {
					_e.printStackTrace();
				}
				if (!parts.getString("5", "").equals(String.valueOf((long)(lm5.size())))) {
					imageview17.setAlpha((float)(1));
				}
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
		msg6.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				lm6 = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						lm6.add(_map);
					}
				}
				catch (Exception _e) {
					_e.printStackTrace();
				}
				if (!parts.getString("6", "").equals(String.valueOf((long)(lm6.size())))) {
					imageview18.setAlpha((float)(1));
				}
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
		msg7.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				lm7 = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						lm7.add(_map);
					}
				}
				catch (Exception _e) {
					_e.printStackTrace();
				}
				if (!parts.getString("7", "").equals(String.valueOf((long)(lm7.size())))) {
					imageview19.setAlpha((float)(1));
				}
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
		msg8.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				lm8 = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						lm8.add(_map);
					}
				}
				catch (Exception _e) {
					_e.printStackTrace();
				}
				if (!parts.getString("8", "").equals(String.valueOf((long)(lm8.size())))) {
					imageview20.setAlpha((float)(1));
				}
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
		msg9.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				lm9 = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						lm9.add(_map);
					}
				}
				catch (Exception _e) {
					_e.printStackTrace();
				}
				if (!parts.getString("9", "").equals(String.valueOf((long)(lm9.size())))) {
					imageview21.setAlpha((float)(1));
				}
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
		msg10.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				lm10 = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						lm10.add(_map);
					}
				}
				catch (Exception _e) {
					_e.printStackTrace();
				}
				if (!parts.getString("10", "").equals(String.valueOf((long)(lm10.size())))) {
					imageview23.setAlpha((float)(1));
				}
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
		msg11.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				lm11 = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						lm11.add(_map);
					}
				}
				catch (Exception _e) {
					_e.printStackTrace();
				}
				if (!parts.getString("11", "").equals(String.valueOf((long)(lm11.size())))) {
					imageview24.setAlpha((float)(1));
				}
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		
	}
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}