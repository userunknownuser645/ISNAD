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
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.EditText;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import android.app.Activity;
import android.content.SharedPreferences;
import android.app.AlertDialog;
import android.content.DialogInterface;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import android.content.Intent;
import android.content.ClipData;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.OnProgressListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Continuation;
import android.net.Uri;
import java.io.File;
import android.os.Bundle;
import java.io.InputStream;
import android.view.View;
import android.text.Editable;
import android.text.TextWatcher;
import com.bumptech.glide.Glide;
import android.content.ClipboardManager;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;


public class HomeActivity extends AppCompatActivity {
	public final int REQ_CD_FP = 101;
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private FirebaseStorage _firebase_storage = FirebaseStorage.getInstance();
	
	private HashMap<String, Object> map = new HashMap<>();
	private String name = "";
	private double imgshow = 0;
	private String path = "";
	private String randomv = "";
	private String uotrandom = "";
	
	private ArrayList<HashMap<String, Object>> lm = new ArrayList<>();
	private ArrayList<String> ls = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> lm2 = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private ListView listview;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private ImageView imageview6;
	private ProgressBar progressbar1;
	private LinearLayout linear7;
	private LinearLayout linear4;
	private TextView textview1;
	private ImageView imageview1;
	private ImageView imageview3;
	private EditText edittext1;
	
	private DatabaseReference msg = _firebase.getReference("msg");
	private ChildEventListener _msg_child_listener;
	private SharedPreferences login;
	private AlertDialog.Builder d;
	private DatabaseReference code = _firebase.getReference("code");
	private ChildEventListener _code_child_listener;
	private TimerTask t;
	private Calendar c = Calendar.getInstance();
	private Intent fp = new Intent(Intent.ACTION_GET_CONTENT);
	private StorageReference pic = _firebase_storage.getReference("pic");
	private OnCompleteListener<Uri> _pic_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _pic_download_success_listener;
	private OnSuccessListener _pic_delete_success_listener;
	private OnProgressListener _pic_upload_progress_listener;
	private OnProgressListener _pic_download_progress_listener;
	private OnFailureListener _pic_failure_listener;
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
	private SharedPreferences parts;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.home);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
		}
		else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		listview = (ListView) findViewById(R.id.listview);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		imageview6 = (ImageView) findViewById(R.id.imageview6);
		progressbar1 = (ProgressBar) findViewById(R.id.progressbar1);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		textview1 = (TextView) findViewById(R.id.textview1);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		imageview3 = (ImageView) findViewById(R.id.imageview3);
		edittext1 = (EditText) findViewById(R.id.edittext1);
		login = getSharedPreferences("login", Activity.MODE_PRIVATE);
		d = new AlertDialog.Builder(this);
		fp.setType("image/*");
		fp.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		parts = getSharedPreferences("parts", Activity.MODE_PRIVATE);
		
		imageview6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(fp, REQ_CD_FP);
			}
		});
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				  try {
					  if ((edittext1.getText().toString().length() > 0) && (path.length() == 0)) {
						map.put("name", AESCrypt.encrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", login.getString("name", "")));
						map.put("img", AESCrypt.encrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", login.getString("img", "")));
						map.put("msg", AESCrypt.encrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", edittext1.getText().toString()));
						map.put("pic", "");
						c = Calendar.getInstance();
						map.put("time", AESCrypt.encrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", new SimpleDateFormat("YYYY/MM/dd   •   hh:mm:ss").format(c.getTime())));
						if (getIntent().getStringExtra("part").equals("1")) {
							msg.addListenerForSingleValueEvent(new ValueEventListener() {
								@Override
								public void onDataChange(DataSnapshot _dataSnapshot) {
									lm = new ArrayList<>();
									try {
										GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
										for (DataSnapshot _data : _dataSnapshot.getChildren()) {
											HashMap<String, Object> _map = _data.getValue(_ind);
											lm.add(_map);
										}
									}
									catch (Exception _e) {
										_e.printStackTrace();
									}
									if (lm.size() > 0) {
										map.put("del", String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)));
										msg.child("m".concat(String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)))).updateChildren(map);
									}
									else {
										map.put("del", "0");
										msg.child("m0").updateChildren(map);
									}
									edittext1.setText("");
								}
								@Override
								public void onCancelled(DatabaseError _databaseError) {
								}
							});
						}
						else {
							if (getIntent().getStringExtra("part").equals("2")) {
								msg2.addListenerForSingleValueEvent(new ValueEventListener() {
									@Override
									public void onDataChange(DataSnapshot _dataSnapshot) {
										lm = new ArrayList<>();
										try {
											GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
											for (DataSnapshot _data : _dataSnapshot.getChildren()) {
												HashMap<String, Object> _map = _data.getValue(_ind);
												lm.add(_map);
											}
										}
										catch (Exception _e) {
											_e.printStackTrace();
										}
										if (lm.size() > 0) {
											map.put("del", String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)));
											msg2.child("m".concat(String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)))).updateChildren(map);
										}
										else {
											map.put("del", "0");
											msg2.child("m0").updateChildren(map);
										}
										edittext1.setText("");
									}
									@Override
									public void onCancelled(DatabaseError _databaseError) {
									}
								});
							}
							else {
								if (getIntent().getStringExtra("part").equals("3")) {
									msg3.addListenerForSingleValueEvent(new ValueEventListener() {
										@Override
										public void onDataChange(DataSnapshot _dataSnapshot) {
											lm = new ArrayList<>();
											try {
												GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
												for (DataSnapshot _data : _dataSnapshot.getChildren()) {
													HashMap<String, Object> _map = _data.getValue(_ind);
													lm.add(_map);
												}
											}
											catch (Exception _e) {
												_e.printStackTrace();
											}
											if (lm.size() > 0) {
												map.put("del", String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)));
												msg3.child("m".concat(String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)))).updateChildren(map);
											}
											else {
												map.put("del", "0");
												msg3.child("m0").updateChildren(map);
											}
											edittext1.setText("");
										}
										@Override
										public void onCancelled(DatabaseError _databaseError) {
										}
									});
								}
								else {
									if (getIntent().getStringExtra("part").equals("4")) {
										msg4.addListenerForSingleValueEvent(new ValueEventListener() {
											@Override
											public void onDataChange(DataSnapshot _dataSnapshot) {
												lm = new ArrayList<>();
												try {
													GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
													for (DataSnapshot _data : _dataSnapshot.getChildren()) {
														HashMap<String, Object> _map = _data.getValue(_ind);
														lm.add(_map);
													}
												}
												catch (Exception _e) {
													_e.printStackTrace();
												}
												if (lm.size() > 0) {
													map.put("del", String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)));
													msg4.child("m".concat(String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)))).updateChildren(map);
												}
												else {
													map.put("del", "0");
													msg4.child("m0").updateChildren(map);
												}
												edittext1.setText("");
											}
											@Override
											public void onCancelled(DatabaseError _databaseError) {
											}
										});
									}
									else {
										if (getIntent().getStringExtra("part").equals("5")) {
											msg5.addListenerForSingleValueEvent(new ValueEventListener() {
												@Override
												public void onDataChange(DataSnapshot _dataSnapshot) {
													lm = new ArrayList<>();
													try {
														GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
														for (DataSnapshot _data : _dataSnapshot.getChildren()) {
															HashMap<String, Object> _map = _data.getValue(_ind);
															lm.add(_map);
														}
													}
													catch (Exception _e) {
														_e.printStackTrace();
													}
													if (lm.size() > 0) {
														map.put("del", String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)));
														msg5.child("m".concat(String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)))).updateChildren(map);
													}
													else {
														map.put("del", "0");
														msg5.child("m0").updateChildren(map);
													}
													edittext1.setText("");
												}
												@Override
												public void onCancelled(DatabaseError _databaseError) {
												}
											});
										}
										else {
											if (getIntent().getStringExtra("part").equals("6")) {
												msg6.addListenerForSingleValueEvent(new ValueEventListener() {
													@Override
													public void onDataChange(DataSnapshot _dataSnapshot) {
														lm = new ArrayList<>();
														try {
															GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
															for (DataSnapshot _data : _dataSnapshot.getChildren()) {
																HashMap<String, Object> _map = _data.getValue(_ind);
																lm.add(_map);
															}
														}
														catch (Exception _e) {
															_e.printStackTrace();
														}
														if (lm.size() > 0) {
															map.put("del", String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)));
															msg6.child("m".concat(String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)))).updateChildren(map);
														}
														else {
															map.put("del", "0");
															msg6.child("m0").updateChildren(map);
														}
														edittext1.setText("");
													}
													@Override
													public void onCancelled(DatabaseError _databaseError) {
													}
												});
											}
											else {
												if (getIntent().getStringExtra("part").equals("7")) {
													msg7.addListenerForSingleValueEvent(new ValueEventListener() {
														@Override
														public void onDataChange(DataSnapshot _dataSnapshot) {
															lm = new ArrayList<>();
															try {
																GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
																for (DataSnapshot _data : _dataSnapshot.getChildren()) {
																	HashMap<String, Object> _map = _data.getValue(_ind);
																	lm.add(_map);
																}
															}
															catch (Exception _e) {
																_e.printStackTrace();
															}
															if (lm.size() > 0) {
																map.put("del", String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)));
																msg7.child("m".concat(String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)))).updateChildren(map);
															}
															else {
																map.put("del", "0");
																msg7.child("m0").updateChildren(map);
															}
															edittext1.setText("");
														}
														@Override
														public void onCancelled(DatabaseError _databaseError) {
														}
													});
												}
												else {
													if (getIntent().getStringExtra("part").equals("8")) {
														msg8.addListenerForSingleValueEvent(new ValueEventListener() {
															@Override
															public void onDataChange(DataSnapshot _dataSnapshot) {
																lm = new ArrayList<>();
																try {
																	GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
																	for (DataSnapshot _data : _dataSnapshot.getChildren()) {
																		HashMap<String, Object> _map = _data.getValue(_ind);
																		lm.add(_map);
																	}
																}
																catch (Exception _e) {
																	_e.printStackTrace();
																}
																if (lm.size() > 0) {
																	map.put("del", String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)));
																	msg8.child("m".concat(String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)))).updateChildren(map);
																}
																else {
																	map.put("del", "0");
																	msg8.child("m0").updateChildren(map);
																}
																edittext1.setText("");
															}
															@Override
															public void onCancelled(DatabaseError _databaseError) {
															}
														});
													}
													else {
														if (getIntent().getStringExtra("part").equals("9")) {
															msg9.addListenerForSingleValueEvent(new ValueEventListener() {
																@Override
																public void onDataChange(DataSnapshot _dataSnapshot) {
																	lm = new ArrayList<>();
																	try {
																		GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
																		for (DataSnapshot _data : _dataSnapshot.getChildren()) {
																			HashMap<String, Object> _map = _data.getValue(_ind);
																			lm.add(_map);
																		}
																	}
																	catch (Exception _e) {
																		_e.printStackTrace();
																	}
																	if (lm.size() > 0) {
																		map.put("del", String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)));
																		msg9.child("m".concat(String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)))).updateChildren(map);
																	}
																	else {
																		map.put("del", "0");
																		msg9.child("m0").updateChildren(map);
																	}
																	edittext1.setText("");
																}
																@Override
																public void onCancelled(DatabaseError _databaseError) {
																}
															});
														}
														else {
															if (getIntent().getStringExtra("part").equals("10")) {
																msg10.addListenerForSingleValueEvent(new ValueEventListener() {
																	@Override
																	public void onDataChange(DataSnapshot _dataSnapshot) {
																		lm = new ArrayList<>();
																		try {
																			GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
																			for (DataSnapshot _data : _dataSnapshot.getChildren()) {
																				HashMap<String, Object> _map = _data.getValue(_ind);
																				lm.add(_map);
																			}
																		}
																		catch (Exception _e) {
																			_e.printStackTrace();
																		}
																		if (lm.size() > 0) {
																			map.put("del", String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)));
																			msg10.child("m".concat(String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)))).updateChildren(map);
																		}
																		else {
																			map.put("del", "0");
																			msg10.child("m0").updateChildren(map);
																		}
																		edittext1.setText("");
																	}
																	@Override
																	public void onCancelled(DatabaseError _databaseError) {
																	}
																});
															}
															else {
																if (getIntent().getStringExtra("part").equals("11")) {
																	msg11.addListenerForSingleValueEvent(new ValueEventListener() {
																		@Override
																		public void onDataChange(DataSnapshot _dataSnapshot) {
																			lm = new ArrayList<>();
																			try {
																				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
																				for (DataSnapshot _data : _dataSnapshot.getChildren()) {
																					HashMap<String, Object> _map = _data.getValue(_ind);
																					lm.add(_map);
																				}
																			}
																			catch (Exception _e) {
																				_e.printStackTrace();
																			}
																			if (lm.size() > 0) {
																				map.put("del", String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)));
																				msg11.child("m".concat(String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)))).updateChildren(map);
																			}
																			else {
																				map.put("del", "0");
																				msg11.child("m0").updateChildren(map);
																			}
																			edittext1.setText("");
																		}
																		@Override
																		public void onCancelled(DatabaseError _databaseError) {
																		}
																	});
																}
																else {
																	
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					else {
						if (path.length() > 0) {
							if (getIntent().getStringExtra("part").equals("1")) {
								msg.addListenerForSingleValueEvent(new ValueEventListener() {
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
										if (lm2.size() > 0) {
											pic.child("p".concat(getIntent().getStringExtra("part").concat("m".concat(String.valueOf((long)(Double.parseDouble(lm2.get((int)lm2.size() - 1).get("del").toString()) + 1)))))).putFile(Uri.fromFile(new File(path))).addOnFailureListener(_pic_failure_listener).addOnProgressListener(_pic_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
												@Override
												public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
													return pic.child("p".concat(getIntent().getStringExtra("part").concat("m".concat(String.valueOf((long)(Double.parseDouble(lm2.get((int)lm2.size() - 1).get("del").toString()) + 1)))))).getDownloadUrl();
												}}).addOnCompleteListener(_pic_upload_success_listener);
											progressbar1.setVisibility(View.VISIBLE);
											imageview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF546E7A));
											imageview1.setEnabled(false);
											imageview3.setEnabled(false);
										}
										else {
											pic.child("p".concat(getIntent().getStringExtra("part").concat("m0"))).putFile(Uri.fromFile(new File(path))).addOnFailureListener(_pic_failure_listener).addOnProgressListener(_pic_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
												@Override
												public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
													return pic.child("p".concat(getIntent().getStringExtra("part").concat("m0"))).getDownloadUrl();
												}}).addOnCompleteListener(_pic_upload_success_listener);
											progressbar1.setVisibility(View.VISIBLE);
											imageview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF546E7A));
											imageview1.setEnabled(false);
											imageview3.setEnabled(false);
										}
									}
									@Override
									public void onCancelled(DatabaseError _databaseError) {
									}
								});
							}
							else {
								if (getIntent().getStringExtra("part").equals("2")) {
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
											if (lm2.size() > 0) {
												pic.child("p".concat(getIntent().getStringExtra("part").concat("m".concat(String.valueOf((long)(Double.parseDouble(lm2.get((int)lm2.size() - 1).get("del").toString()) + 1)))))).putFile(Uri.fromFile(new File(path))).addOnFailureListener(_pic_failure_listener).addOnProgressListener(_pic_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
													@Override
													public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
														return pic.child("p".concat(getIntent().getStringExtra("part").concat("m".concat(String.valueOf((long)(Double.parseDouble(lm2.get((int)lm2.size() - 1).get("del").toString()) + 1)))))).getDownloadUrl();
													}}).addOnCompleteListener(_pic_upload_success_listener);
												progressbar1.setVisibility(View.VISIBLE);
												imageview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF546E7A));
												imageview1.setEnabled(false);
												imageview3.setEnabled(false);
											}
											else {
												pic.child("p".concat(getIntent().getStringExtra("part").concat("m0"))).putFile(Uri.fromFile(new File(path))).addOnFailureListener(_pic_failure_listener).addOnProgressListener(_pic_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
													@Override
													public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
														return pic.child("p".concat(getIntent().getStringExtra("part").concat("m0"))).getDownloadUrl();
													}}).addOnCompleteListener(_pic_upload_success_listener);
												progressbar1.setVisibility(View.VISIBLE);
												imageview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF546E7A));
												imageview1.setEnabled(false);
												imageview3.setEnabled(false);
											}
										}
										@Override
										public void onCancelled(DatabaseError _databaseError) {
										}
									});
								}
								else {
									if (getIntent().getStringExtra("part").equals("3")) {
										msg3.addListenerForSingleValueEvent(new ValueEventListener() {
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
												if (lm2.size() > 0) {
													pic.child("p".concat(getIntent().getStringExtra("part").concat("m".concat(String.valueOf((long)(Double.parseDouble(lm2.get((int)lm2.size() - 1).get("del").toString()) + 1)))))).putFile(Uri.fromFile(new File(path))).addOnFailureListener(_pic_failure_listener).addOnProgressListener(_pic_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
														@Override
														public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
															return pic.child("p".concat(getIntent().getStringExtra("part").concat("m".concat(String.valueOf((long)(Double.parseDouble(lm2.get((int)lm2.size() - 1).get("del").toString()) + 1)))))).getDownloadUrl();
														}}).addOnCompleteListener(_pic_upload_success_listener);
													progressbar1.setVisibility(View.VISIBLE);
													imageview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF546E7A));
													imageview1.setEnabled(false);
													imageview3.setEnabled(false);
												}
												else {
													pic.child("p".concat(getIntent().getStringExtra("part").concat("m0"))).putFile(Uri.fromFile(new File(path))).addOnFailureListener(_pic_failure_listener).addOnProgressListener(_pic_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
														@Override
														public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
															return pic.child("p".concat(getIntent().getStringExtra("part").concat("m0"))).getDownloadUrl();
														}}).addOnCompleteListener(_pic_upload_success_listener);
													progressbar1.setVisibility(View.VISIBLE);
													imageview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF546E7A));
													imageview1.setEnabled(false);
													imageview3.setEnabled(false);
												}
											}
											@Override
											public void onCancelled(DatabaseError _databaseError) {
											}
										});
									}
									else {
										if (getIntent().getStringExtra("part").equals("4")) {
											msg4.addListenerForSingleValueEvent(new ValueEventListener() {
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
													if (lm2.size() > 0) {
														pic.child("p".concat(getIntent().getStringExtra("part").concat("m".concat(String.valueOf((long)(Double.parseDouble(lm2.get((int)lm2.size() - 1).get("del").toString()) + 1)))))).putFile(Uri.fromFile(new File(path))).addOnFailureListener(_pic_failure_listener).addOnProgressListener(_pic_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
															@Override
															public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
																return pic.child("p".concat(getIntent().getStringExtra("part").concat("m".concat(String.valueOf((long)(Double.parseDouble(lm2.get((int)lm2.size() - 1).get("del").toString()) + 1)))))).getDownloadUrl();
															}}).addOnCompleteListener(_pic_upload_success_listener);
														progressbar1.setVisibility(View.VISIBLE);
														imageview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF546E7A));
														imageview1.setEnabled(false);
														imageview3.setEnabled(false);
													}
													else {
														pic.child("p".concat(getIntent().getStringExtra("part").concat("m0"))).putFile(Uri.fromFile(new File(path))).addOnFailureListener(_pic_failure_listener).addOnProgressListener(_pic_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
															@Override
															public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
																return pic.child("p".concat(getIntent().getStringExtra("part").concat("m0"))).getDownloadUrl();
															}}).addOnCompleteListener(_pic_upload_success_listener);
														progressbar1.setVisibility(View.VISIBLE);
														imageview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF546E7A));
														imageview1.setEnabled(false);
														imageview3.setEnabled(false);
													}
												}
												@Override
												public void onCancelled(DatabaseError _databaseError) {
												}
											});
										}
										else {
											if (getIntent().getStringExtra("part").equals("5")) {
												msg5.addListenerForSingleValueEvent(new ValueEventListener() {
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
														if (lm2.size() > 0) {
															pic.child("p".concat(getIntent().getStringExtra("part").concat("m".concat(String.valueOf((long)(Double.parseDouble(lm2.get((int)lm2.size() - 1).get("del").toString()) + 1)))))).putFile(Uri.fromFile(new File(path))).addOnFailureListener(_pic_failure_listener).addOnProgressListener(_pic_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
																@Override
																public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
																	return pic.child("p".concat(getIntent().getStringExtra("part").concat("m".concat(String.valueOf((long)(Double.parseDouble(lm2.get((int)lm2.size() - 1).get("del").toString()) + 1)))))).getDownloadUrl();
																}}).addOnCompleteListener(_pic_upload_success_listener);
															progressbar1.setVisibility(View.VISIBLE);
															imageview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF546E7A));
															imageview1.setEnabled(false);
															imageview3.setEnabled(false);
														}
														else {
															pic.child("p".concat(getIntent().getStringExtra("part").concat("m0"))).putFile(Uri.fromFile(new File(path))).addOnFailureListener(_pic_failure_listener).addOnProgressListener(_pic_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
																@Override
																public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
																	return pic.child("p".concat(getIntent().getStringExtra("part").concat("m0"))).getDownloadUrl();
																}}).addOnCompleteListener(_pic_upload_success_listener);
															progressbar1.setVisibility(View.VISIBLE);
															imageview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF546E7A));
															imageview1.setEnabled(false);
															imageview3.setEnabled(false);
														}
													}
													@Override
													public void onCancelled(DatabaseError _databaseError) {
													}
												});
											}
											else {
												if (getIntent().getStringExtra("part").equals("6")) {
													msg6.addListenerForSingleValueEvent(new ValueEventListener() {
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
															if (lm2.size() > 0) {
																pic.child("p".concat(getIntent().getStringExtra("part").concat("m".concat(String.valueOf((long)(Double.parseDouble(lm2.get((int)lm2.size() - 1).get("del").toString()) + 1)))))).putFile(Uri.fromFile(new File(path))).addOnFailureListener(_pic_failure_listener).addOnProgressListener(_pic_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
																	@Override
																	public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
																		return pic.child("p".concat(getIntent().getStringExtra("part").concat("m".concat(String.valueOf((long)(Double.parseDouble(lm2.get((int)lm2.size() - 1).get("del").toString()) + 1)))))).getDownloadUrl();
																	}}).addOnCompleteListener(_pic_upload_success_listener);
																progressbar1.setVisibility(View.VISIBLE);
																imageview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF546E7A));
																imageview1.setEnabled(false);
																imageview3.setEnabled(false);
															}
															else {
																pic.child("p".concat(getIntent().getStringExtra("part").concat("m0"))).putFile(Uri.fromFile(new File(path))).addOnFailureListener(_pic_failure_listener).addOnProgressListener(_pic_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
																	@Override
																	public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
																		return pic.child("p".concat(getIntent().getStringExtra("part").concat("m0"))).getDownloadUrl();
																	}}).addOnCompleteListener(_pic_upload_success_listener);
																progressbar1.setVisibility(View.VISIBLE);
																imageview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF546E7A));
																imageview1.setEnabled(false);
																imageview3.setEnabled(false);
															}
														}
														@Override
														public void onCancelled(DatabaseError _databaseError) {
														}
													});
												}
												else {
													if (getIntent().getStringExtra("part").equals("7")) {
														msg7.addListenerForSingleValueEvent(new ValueEventListener() {
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
																if (lm2.size() > 0) {
																	pic.child("p".concat(getIntent().getStringExtra("part").concat("m".concat(String.valueOf((long)(Double.parseDouble(lm2.get((int)lm2.size() - 1).get("del").toString()) + 1)))))).putFile(Uri.fromFile(new File(path))).addOnFailureListener(_pic_failure_listener).addOnProgressListener(_pic_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
																		@Override
																		public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
																			return pic.child("p".concat(getIntent().getStringExtra("part").concat("m".concat(String.valueOf((long)(Double.parseDouble(lm2.get((int)lm2.size() - 1).get("del").toString()) + 1)))))).getDownloadUrl();
																		}}).addOnCompleteListener(_pic_upload_success_listener);
																	progressbar1.setVisibility(View.VISIBLE);
																	imageview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF546E7A));
																	imageview1.setEnabled(false);
																	imageview3.setEnabled(false);
																}
																else {
																	pic.child("p".concat(getIntent().getStringExtra("part").concat("m0"))).putFile(Uri.fromFile(new File(path))).addOnFailureListener(_pic_failure_listener).addOnProgressListener(_pic_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
																		@Override
																		public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
																			return pic.child("p".concat(getIntent().getStringExtra("part").concat("m0"))).getDownloadUrl();
																		}}).addOnCompleteListener(_pic_upload_success_listener);
																	progressbar1.setVisibility(View.VISIBLE);
																	imageview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF546E7A));
																	imageview1.setEnabled(false);
																	imageview3.setEnabled(false);
																}
															}
															@Override
															public void onCancelled(DatabaseError _databaseError) {
															}
														});
													}
													else {
														if (getIntent().getStringExtra("part").equals("8")) {
															msg8.addListenerForSingleValueEvent(new ValueEventListener() {
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
																	if (lm2.size() > 0) {
																		pic.child("p".concat(getIntent().getStringExtra("part").concat("m".concat(String.valueOf((long)(Double.parseDouble(lm2.get((int)lm2.size() - 1).get("del").toString()) + 1)))))).putFile(Uri.fromFile(new File(path))).addOnFailureListener(_pic_failure_listener).addOnProgressListener(_pic_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
																			@Override
																			public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
																				return pic.child("p".concat(getIntent().getStringExtra("part").concat("m".concat(String.valueOf((long)(Double.parseDouble(lm2.get((int)lm2.size() - 1).get("del").toString()) + 1)))))).getDownloadUrl();
																			}}).addOnCompleteListener(_pic_upload_success_listener);
																		progressbar1.setVisibility(View.VISIBLE);
																		imageview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF546E7A));
																		imageview1.setEnabled(false);
																		imageview3.setEnabled(false);
																	}
																	else {
																		pic.child("p".concat(getIntent().getStringExtra("part").concat("m0"))).putFile(Uri.fromFile(new File(path))).addOnFailureListener(_pic_failure_listener).addOnProgressListener(_pic_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
																			@Override
																			public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
																				return pic.child("p".concat(getIntent().getStringExtra("part").concat("m0"))).getDownloadUrl();
																			}}).addOnCompleteListener(_pic_upload_success_listener);
																		progressbar1.setVisibility(View.VISIBLE);
																		imageview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF546E7A));
																		imageview1.setEnabled(false);
																		imageview3.setEnabled(false);
																	}
																}
																@Override
																public void onCancelled(DatabaseError _databaseError) {
																}
															});
														}
														else {
															if (getIntent().getStringExtra("part").equals("9")) {
																msg9.addListenerForSingleValueEvent(new ValueEventListener() {
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
																		if (lm2.size() > 0) {
																			pic.child("p".concat(getIntent().getStringExtra("part").concat("m".concat(String.valueOf((long)(Double.parseDouble(lm2.get((int)lm2.size() - 1).get("del").toString()) + 1)))))).putFile(Uri.fromFile(new File(path))).addOnFailureListener(_pic_failure_listener).addOnProgressListener(_pic_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
																				@Override
																				public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
																					return pic.child("p".concat(getIntent().getStringExtra("part").concat("m".concat(String.valueOf((long)(Double.parseDouble(lm2.get((int)lm2.size() - 1).get("del").toString()) + 1)))))).getDownloadUrl();
																				}}).addOnCompleteListener(_pic_upload_success_listener);
																			progressbar1.setVisibility(View.VISIBLE);
																			imageview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF546E7A));
																			imageview1.setEnabled(false);
																			imageview3.setEnabled(false);
																		}
																		else {
																			pic.child("p".concat(getIntent().getStringExtra("part").concat("m0"))).putFile(Uri.fromFile(new File(path))).addOnFailureListener(_pic_failure_listener).addOnProgressListener(_pic_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
																				@Override
																				public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
																					return pic.child("p".concat(getIntent().getStringExtra("part").concat("m0"))).getDownloadUrl();
																				}}).addOnCompleteListener(_pic_upload_success_listener);
																			progressbar1.setVisibility(View.VISIBLE);
																			imageview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF546E7A));
																			imageview1.setEnabled(false);
																			imageview3.setEnabled(false);
																		}
																	}
																	@Override
																	public void onCancelled(DatabaseError _databaseError) {
																	}
																});
															}
															else {
																if (getIntent().getStringExtra("part").equals("10")) {
																	msg10.addListenerForSingleValueEvent(new ValueEventListener() {
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
																			if (lm2.size() > 0) {
																				pic.child("p".concat(getIntent().getStringExtra("part").concat("m".concat(String.valueOf((long)(Double.parseDouble(lm2.get((int)lm2.size() - 1).get("del").toString()) + 1)))))).putFile(Uri.fromFile(new File(path))).addOnFailureListener(_pic_failure_listener).addOnProgressListener(_pic_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
																					@Override
																					public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
																						return pic.child("p".concat(getIntent().getStringExtra("part").concat("m".concat(String.valueOf((long)(Double.parseDouble(lm2.get((int)lm2.size() - 1).get("del").toString()) + 1)))))).getDownloadUrl();
																					}}).addOnCompleteListener(_pic_upload_success_listener);
																				progressbar1.setVisibility(View.VISIBLE);
																				imageview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF546E7A));
																				imageview1.setEnabled(false);
																				imageview3.setEnabled(false);
																			}
																			else {
																				pic.child("p".concat(getIntent().getStringExtra("part").concat("m0"))).putFile(Uri.fromFile(new File(path))).addOnFailureListener(_pic_failure_listener).addOnProgressListener(_pic_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
																					@Override
																					public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
																						return pic.child("p".concat(getIntent().getStringExtra("part").concat("m0"))).getDownloadUrl();
																					}}).addOnCompleteListener(_pic_upload_success_listener);
																				progressbar1.setVisibility(View.VISIBLE);
																				imageview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF546E7A));
																				imageview1.setEnabled(false);
																				imageview3.setEnabled(false);
																			}
																		}
																		@Override
																		public void onCancelled(DatabaseError _databaseError) {
																		}
																	});
																}
																else {
																	if (getIntent().getStringExtra("part").equals("11")) {
																		msg11.addListenerForSingleValueEvent(new ValueEventListener() {
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
																				if (lm2.size() > 0) {
																					pic.child("p".concat(getIntent().getStringExtra("part").concat("m".concat(String.valueOf((long)(Double.parseDouble(lm2.get((int)lm2.size() - 1).get("del").toString()) + 1)))))).putFile(Uri.fromFile(new File(path))).addOnFailureListener(_pic_failure_listener).addOnProgressListener(_pic_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
																						@Override
																						public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
																							return pic.child("p".concat(getIntent().getStringExtra("part").concat("m".concat(String.valueOf((long)(Double.parseDouble(lm2.get((int)lm2.size() - 1).get("del").toString()) + 1)))))).getDownloadUrl();
																						}}).addOnCompleteListener(_pic_upload_success_listener);
																					progressbar1.setVisibility(View.VISIBLE);
																					imageview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF546E7A));
																					imageview1.setEnabled(false);
																					imageview3.setEnabled(false);
																				}
																				else {
																					pic.child("p".concat(getIntent().getStringExtra("part").concat("m0"))).putFile(Uri.fromFile(new File(path))).addOnFailureListener(_pic_failure_listener).addOnProgressListener(_pic_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
																						@Override
																						public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
																							return pic.child("p".concat(getIntent().getStringExtra("part").concat("m0"))).getDownloadUrl();
																						}}).addOnCompleteListener(_pic_upload_success_listener);
																					progressbar1.setVisibility(View.VISIBLE);
																					imageview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF546E7A));
																					imageview1.setEnabled(false);
																					imageview3.setEnabled(false);
																				}
																			}
																			@Override
																			public void onCancelled(DatabaseError _databaseError) {
																			}
																		});
																	}
																	else {
																		
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
						else {
							
						}
					}
					  } catch (java.security.GeneralSecurityException e){
					      showMessage("password incorrect !" + "\n" + e.toString());
					  }
			}
		});
		
		imageview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (imgshow == 1) {
					imageview6.setImageResource(R.drawable.addimg);
					path = "";
					imageview3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF455A64));
					linear5.setVisibility(View.GONE);
					imgshow = 0;
				}
				else {
					imageview6.setImageResource(R.drawable.addimg);
					path = "";
					imageview3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF546E7A));
					linear5.setVisibility(View.VISIBLE);
					imgshow = 1;
				}
			}
		});
		
		edittext1.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				login.edit().putString("text", edittext1.getText().toString()).commit();
				if (edittext1.getText().toString().contains("\n")) {
					edittext1.setLines(7);
				}
				else {
					edittext1.setLines(2);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		_msg_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (getIntent().getStringExtra("part").equals("1")) {
					msg.addListenerForSingleValueEvent(new ValueEventListener() {
						@Override
						public void onDataChange(DataSnapshot _dataSnapshot) {
							lm = new ArrayList<>();
							try {
								GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								for (DataSnapshot _data : _dataSnapshot.getChildren()) {
									HashMap<String, Object> _map = _data.getValue(_ind);
									lm.add(_map);
								}
							}
							catch (Exception _e) {
								_e.printStackTrace();
							}
							parts.edit().putString("1", String.valueOf((long)(lm.size()))).commit();
							Collections.reverse(lm);
							listview.setAdapter(new ListviewAdapter(lm));
						}
						@Override
						public void onCancelled(DatabaseError _databaseError) {
						}
					});
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (getIntent().getStringExtra("part").equals("1")) {
					msg.addListenerForSingleValueEvent(new ValueEventListener() {
						@Override
						public void onDataChange(DataSnapshot _dataSnapshot) {
							lm = new ArrayList<>();
							try {
								GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								for (DataSnapshot _data : _dataSnapshot.getChildren()) {
									HashMap<String, Object> _map = _data.getValue(_ind);
									lm.add(_map);
								}
							}
							catch (Exception _e) {
								_e.printStackTrace();
							}
							parts.edit().putString("1", String.valueOf((long)(lm.size()))).commit();
							Collections.reverse(lm);
							listview.setAdapter(new ListviewAdapter(lm));
						}
						@Override
						public void onCancelled(DatabaseError _databaseError) {
						}
					});
				}
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (getIntent().getStringExtra("part").equals("1")) {
					msg.addListenerForSingleValueEvent(new ValueEventListener() {
						@Override
						public void onDataChange(DataSnapshot _dataSnapshot) {
							lm = new ArrayList<>();
							try {
								GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								for (DataSnapshot _data : _dataSnapshot.getChildren()) {
									HashMap<String, Object> _map = _data.getValue(_ind);
									lm.add(_map);
								}
							}
							catch (Exception _e) {
								_e.printStackTrace();
							}
							parts.edit().putString("1", String.valueOf((long)(lm.size()))).commit();
							Collections.reverse(lm);
							listview.setAdapter(new ListviewAdapter(lm));
						}
						@Override
						public void onCancelled(DatabaseError _databaseError) {
						}
					});
				}
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		msg.addChildEventListener(_msg_child_listener);
		
		_code_child_listener = new ChildEventListener() {
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
		code.addChildEventListener(_code_child_listener);
		
		_pic_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				progressbar1.setProgress((int)_progressValue);
			}
		};
		
		_pic_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_pic_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				  try {
					  map.put("name", AESCrypt.encrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", login.getString("name", "")));
					map.put("img", AESCrypt.encrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", login.getString("img", "")));
					map.put("msg", AESCrypt.encrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", edittext1.getText().toString()));
					map.put("pic", AESCrypt.encrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", _downloadUrl));
					c = Calendar.getInstance();
					map.put("time", AESCrypt.encrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", new SimpleDateFormat("YYYY/MM/dd   •   hh:mm:ss").format(c.getTime())));
					if (getIntent().getStringExtra("part").equals("1")) {
						msg.addListenerForSingleValueEvent(new ValueEventListener() {
							@Override
							public void onDataChange(DataSnapshot _dataSnapshot) {
								lm = new ArrayList<>();
								try {
									GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
									for (DataSnapshot _data : _dataSnapshot.getChildren()) {
										HashMap<String, Object> _map = _data.getValue(_ind);
										lm.add(_map);
									}
								}
								catch (Exception _e) {
									_e.printStackTrace();
								}
								if (lm.size() > 0) {
									map.put("del", String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)));
									msg.child("m".concat(String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)))).updateChildren(map);
								}
								else {
									map.put("del", "0");
									msg.child("m0").updateChildren(map);
								}
								edittext1.setText("");
								progressbar1.setVisibility(View.GONE);
								linear5.setVisibility(View.GONE);
								imageview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF455A64));
								imageview3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF455A64));
								imageview1.setEnabled(true);
								imageview3.setEnabled(true);
								imageview6.setImageResource(R.drawable.addimg);
								path = "";
								imgshow = 0;
							}
							@Override
							public void onCancelled(DatabaseError _databaseError) {
							}
						});
					}
					else {
						if (getIntent().getStringExtra("part").equals("2")) {
							msg2.addListenerForSingleValueEvent(new ValueEventListener() {
								@Override
								public void onDataChange(DataSnapshot _dataSnapshot) {
									lm = new ArrayList<>();
									try {
										GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
										for (DataSnapshot _data : _dataSnapshot.getChildren()) {
											HashMap<String, Object> _map = _data.getValue(_ind);
											lm.add(_map);
										}
									}
									catch (Exception _e) {
										_e.printStackTrace();
									}
									if (lm.size() > 0) {
										map.put("del", String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)));
										msg2.child("m".concat(String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)))).updateChildren(map);
									}
									else {
										map.put("del", "0");
										msg2.child("m0").updateChildren(map);
									}
									edittext1.setText("");
									progressbar1.setVisibility(View.GONE);
									linear5.setVisibility(View.GONE);
									imageview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF455A64));
									imageview3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF455A64));
									imageview1.setEnabled(true);
									imageview3.setEnabled(true);
									imageview6.setImageResource(R.drawable.addimg);
									path = "";
									imgshow = 0;
								}
								@Override
								public void onCancelled(DatabaseError _databaseError) {
								}
							});
						}
						else {
							if (getIntent().getStringExtra("part").equals("3")) {
								msg3.addListenerForSingleValueEvent(new ValueEventListener() {
									@Override
									public void onDataChange(DataSnapshot _dataSnapshot) {
										lm = new ArrayList<>();
										try {
											GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
											for (DataSnapshot _data : _dataSnapshot.getChildren()) {
												HashMap<String, Object> _map = _data.getValue(_ind);
												lm.add(_map);
											}
										}
										catch (Exception _e) {
											_e.printStackTrace();
										}
										if (lm.size() > 0) {
											map.put("del", String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)));
											msg3.child("m".concat(String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)))).updateChildren(map);
										}
										else {
											map.put("del", "0");
											msg3.child("m0").updateChildren(map);
										}
										edittext1.setText("");
										progressbar1.setVisibility(View.GONE);
										linear5.setVisibility(View.GONE);
										imageview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF455A64));
										imageview3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF455A64));
										imageview1.setEnabled(true);
										imageview3.setEnabled(true);
										imageview6.setImageResource(R.drawable.addimg);
										path = "";
										imgshow = 0;
									}
									@Override
									public void onCancelled(DatabaseError _databaseError) {
									}
								});
							}
							else {
								if (getIntent().getStringExtra("part").equals("4")) {
									msg4.addListenerForSingleValueEvent(new ValueEventListener() {
										@Override
										public void onDataChange(DataSnapshot _dataSnapshot) {
											lm = new ArrayList<>();
											try {
												GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
												for (DataSnapshot _data : _dataSnapshot.getChildren()) {
													HashMap<String, Object> _map = _data.getValue(_ind);
													lm.add(_map);
												}
											}
											catch (Exception _e) {
												_e.printStackTrace();
											}
											if (lm.size() > 0) {
												map.put("del", String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)));
												msg4.child("m".concat(String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)))).updateChildren(map);
											}
											else {
												map.put("del", "0");
												msg4.child("m0").updateChildren(map);
											}
											edittext1.setText("");
											progressbar1.setVisibility(View.GONE);
											linear5.setVisibility(View.GONE);
											imageview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF455A64));
											imageview3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF455A64));
											imageview1.setEnabled(true);
											imageview3.setEnabled(true);
											imageview6.setImageResource(R.drawable.addimg);
											path = "";
											imgshow = 0;
										}
										@Override
										public void onCancelled(DatabaseError _databaseError) {
										}
									});
								}
								else {
									if (getIntent().getStringExtra("part").equals("5")) {
										msg5.addListenerForSingleValueEvent(new ValueEventListener() {
											@Override
											public void onDataChange(DataSnapshot _dataSnapshot) {
												lm = new ArrayList<>();
												try {
													GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
													for (DataSnapshot _data : _dataSnapshot.getChildren()) {
														HashMap<String, Object> _map = _data.getValue(_ind);
														lm.add(_map);
													}
												}
												catch (Exception _e) {
													_e.printStackTrace();
												}
												if (lm.size() > 0) {
													map.put("del", String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)));
													msg5.child("m".concat(String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)))).updateChildren(map);
												}
												else {
													map.put("del", "0");
													msg5.child("m0").updateChildren(map);
												}
												edittext1.setText("");
												progressbar1.setVisibility(View.GONE);
												linear5.setVisibility(View.GONE);
												imageview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF455A64));
												imageview3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF455A64));
												imageview1.setEnabled(true);
												imageview3.setEnabled(true);
												imageview6.setImageResource(R.drawable.addimg);
												path = "";
												imgshow = 0;
											}
											@Override
											public void onCancelled(DatabaseError _databaseError) {
											}
										});
									}
									else {
										if (getIntent().getStringExtra("part").equals("6")) {
											msg6.addListenerForSingleValueEvent(new ValueEventListener() {
												@Override
												public void onDataChange(DataSnapshot _dataSnapshot) {
													lm = new ArrayList<>();
													try {
														GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
														for (DataSnapshot _data : _dataSnapshot.getChildren()) {
															HashMap<String, Object> _map = _data.getValue(_ind);
															lm.add(_map);
														}
													}
													catch (Exception _e) {
														_e.printStackTrace();
													}
													if (lm.size() > 0) {
														map.put("del", String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)));
														msg6.child("m".concat(String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)))).updateChildren(map);
													}
													else {
														map.put("del", "0");
														msg6.child("m0").updateChildren(map);
													}
													edittext1.setText("");
													progressbar1.setVisibility(View.GONE);
													linear5.setVisibility(View.GONE);
													imageview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF455A64));
													imageview3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF455A64));
													imageview1.setEnabled(true);
													imageview3.setEnabled(true);
													imageview6.setImageResource(R.drawable.addimg);
													path = "";
													imgshow = 0;
												}
												@Override
												public void onCancelled(DatabaseError _databaseError) {
												}
											});
										}
										else {
											if (getIntent().getStringExtra("part").equals("7")) {
												msg7.addListenerForSingleValueEvent(new ValueEventListener() {
													@Override
													public void onDataChange(DataSnapshot _dataSnapshot) {
														lm = new ArrayList<>();
														try {
															GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
															for (DataSnapshot _data : _dataSnapshot.getChildren()) {
																HashMap<String, Object> _map = _data.getValue(_ind);
																lm.add(_map);
															}
														}
														catch (Exception _e) {
															_e.printStackTrace();
														}
														if (lm.size() > 0) {
															map.put("del", String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)));
															msg7.child("m".concat(String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)))).updateChildren(map);
														}
														else {
															map.put("del", "0");
															msg7.child("m0").updateChildren(map);
														}
														edittext1.setText("");
														progressbar1.setVisibility(View.GONE);
														linear5.setVisibility(View.GONE);
														imageview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF455A64));
														imageview3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF455A64));
														imageview1.setEnabled(true);
														imageview3.setEnabled(true);
														imageview6.setImageResource(R.drawable.addimg);
														path = "";
														imgshow = 0;
													}
													@Override
													public void onCancelled(DatabaseError _databaseError) {
													}
												});
											}
											else {
												if (getIntent().getStringExtra("part").equals("8")) {
													msg8.addListenerForSingleValueEvent(new ValueEventListener() {
														@Override
														public void onDataChange(DataSnapshot _dataSnapshot) {
															lm = new ArrayList<>();
															try {
																GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
																for (DataSnapshot _data : _dataSnapshot.getChildren()) {
																	HashMap<String, Object> _map = _data.getValue(_ind);
																	lm.add(_map);
																}
															}
															catch (Exception _e) {
																_e.printStackTrace();
															}
															if (lm.size() > 0) {
																map.put("del", String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)));
																msg8.child("m".concat(String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)))).updateChildren(map);
															}
															else {
																map.put("del", "0");
																msg8.child("m0").updateChildren(map);
															}
															edittext1.setText("");
															progressbar1.setVisibility(View.GONE);
															linear5.setVisibility(View.GONE);
															imageview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF455A64));
															imageview3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF455A64));
															imageview1.setEnabled(true);
															imageview3.setEnabled(true);
															imageview6.setImageResource(R.drawable.addimg);
															path = "";
															imgshow = 0;
														}
														@Override
														public void onCancelled(DatabaseError _databaseError) {
														}
													});
												}
												else {
													if (getIntent().getStringExtra("part").equals("9")) {
														msg9.addListenerForSingleValueEvent(new ValueEventListener() {
															@Override
															public void onDataChange(DataSnapshot _dataSnapshot) {
																lm = new ArrayList<>();
																try {
																	GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
																	for (DataSnapshot _data : _dataSnapshot.getChildren()) {
																		HashMap<String, Object> _map = _data.getValue(_ind);
																		lm.add(_map);
																	}
																}
																catch (Exception _e) {
																	_e.printStackTrace();
																}
																if (lm.size() > 0) {
																	map.put("del", String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)));
																	msg9.child("m".concat(String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)))).updateChildren(map);
																}
																else {
																	map.put("del", "0");
																	msg9.child("m0").updateChildren(map);
																}
																edittext1.setText("");
																progressbar1.setVisibility(View.GONE);
																linear5.setVisibility(View.GONE);
																imageview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF455A64));
																imageview3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF455A64));
																imageview1.setEnabled(true);
																imageview3.setEnabled(true);
																imageview6.setImageResource(R.drawable.addimg);
																path = "";
																imgshow = 0;
															}
															@Override
															public void onCancelled(DatabaseError _databaseError) {
															}
														});
													}
													else {
														if (getIntent().getStringExtra("part").equals("10")) {
															msg10.addListenerForSingleValueEvent(new ValueEventListener() {
																@Override
																public void onDataChange(DataSnapshot _dataSnapshot) {
																	lm = new ArrayList<>();
																	try {
																		GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
																		for (DataSnapshot _data : _dataSnapshot.getChildren()) {
																			HashMap<String, Object> _map = _data.getValue(_ind);
																			lm.add(_map);
																		}
																	}
																	catch (Exception _e) {
																		_e.printStackTrace();
																	}
																	if (lm.size() > 0) {
																		map.put("del", String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)));
																		msg10.child("m".concat(String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)))).updateChildren(map);
																	}
																	else {
																		map.put("del", "0");
																		msg10.child("m0").updateChildren(map);
																	}
																	edittext1.setText("");
																	progressbar1.setVisibility(View.GONE);
																	linear5.setVisibility(View.GONE);
																	imageview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF455A64));
																	imageview3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF455A64));
																	imageview1.setEnabled(true);
																	imageview3.setEnabled(true);
																	imageview6.setImageResource(R.drawable.addimg);
																	path = "";
																	imgshow = 0;
																}
																@Override
																public void onCancelled(DatabaseError _databaseError) {
																}
															});
														}
														else {
															if (getIntent().getStringExtra("part").equals("11")) {
																msg11.addListenerForSingleValueEvent(new ValueEventListener() {
																	@Override
																	public void onDataChange(DataSnapshot _dataSnapshot) {
																		lm = new ArrayList<>();
																		try {
																			GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
																			for (DataSnapshot _data : _dataSnapshot.getChildren()) {
																				HashMap<String, Object> _map = _data.getValue(_ind);
																				lm.add(_map);
																			}
																		}
																		catch (Exception _e) {
																			_e.printStackTrace();
																		}
																		if (lm.size() > 0) {
																			map.put("del", String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)));
																			msg11.child("m".concat(String.valueOf((long)(Double.parseDouble(lm.get((int)lm.size() - 1).get("del").toString()) + 1)))).updateChildren(map);
																		}
																		else {
																			map.put("del", "0");
																			msg11.child("m0").updateChildren(map);
																		}
																		edittext1.setText("");
																		progressbar1.setVisibility(View.GONE);
																		linear5.setVisibility(View.GONE);
																		imageview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF455A64));
																		imageview3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF455A64));
																		imageview1.setEnabled(true);
																		imageview3.setEnabled(true);
																		imageview6.setImageResource(R.drawable.addimg);
																		path = "";
																		imgshow = 0;
																	}
																	@Override
																	public void onCancelled(DatabaseError _databaseError) {
																	}
																});
															}
															else {
																
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					  } catch (java.security.GeneralSecurityException e){
					      showMessage("password incorrect !" + "\n" + e.toString());
					  }
			}
		};
		
		_pic_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				SketchwareUtil.showMessage(getApplicationContext(), "تم تحميل الصورة");
			}
		};
		
		_pic_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_pic_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				
			}
		};
		
		_msg2_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (getIntent().getStringExtra("part").equals("2")) {
					msg2.addListenerForSingleValueEvent(new ValueEventListener() {
						@Override
						public void onDataChange(DataSnapshot _dataSnapshot) {
							lm = new ArrayList<>();
							try {
								GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								for (DataSnapshot _data : _dataSnapshot.getChildren()) {
									HashMap<String, Object> _map = _data.getValue(_ind);
									lm.add(_map);
								}
							}
							catch (Exception _e) {
								_e.printStackTrace();
							}
							parts.edit().putString("2", String.valueOf((long)(lm.size()))).commit();
							Collections.reverse(lm);
							listview.setAdapter(new ListviewAdapter(lm));
						}
						@Override
						public void onCancelled(DatabaseError _databaseError) {
						}
					});
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (getIntent().getStringExtra("part").equals("2")) {
					msg2.addListenerForSingleValueEvent(new ValueEventListener() {
						@Override
						public void onDataChange(DataSnapshot _dataSnapshot) {
							lm = new ArrayList<>();
							try {
								GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								for (DataSnapshot _data : _dataSnapshot.getChildren()) {
									HashMap<String, Object> _map = _data.getValue(_ind);
									lm.add(_map);
								}
							}
							catch (Exception _e) {
								_e.printStackTrace();
							}
							parts.edit().putString("2", String.valueOf((long)(lm.size()))).commit();
							Collections.reverse(lm);
							listview.setAdapter(new ListviewAdapter(lm));
						}
						@Override
						public void onCancelled(DatabaseError _databaseError) {
						}
					});
				}
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (getIntent().getStringExtra("part").equals("2")) {
					msg2.addListenerForSingleValueEvent(new ValueEventListener() {
						@Override
						public void onDataChange(DataSnapshot _dataSnapshot) {
							lm = new ArrayList<>();
							try {
								GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								for (DataSnapshot _data : _dataSnapshot.getChildren()) {
									HashMap<String, Object> _map = _data.getValue(_ind);
									lm.add(_map);
								}
							}
							catch (Exception _e) {
								_e.printStackTrace();
							}
							parts.edit().putString("2", String.valueOf((long)(lm.size()))).commit();
							Collections.reverse(lm);
							listview.setAdapter(new ListviewAdapter(lm));
						}
						@Override
						public void onCancelled(DatabaseError _databaseError) {
						}
					});
				}
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
				if (getIntent().getStringExtra("part").equals("3")) {
					msg3.addListenerForSingleValueEvent(new ValueEventListener() {
						@Override
						public void onDataChange(DataSnapshot _dataSnapshot) {
							lm = new ArrayList<>();
							try {
								GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								for (DataSnapshot _data : _dataSnapshot.getChildren()) {
									HashMap<String, Object> _map = _data.getValue(_ind);
									lm.add(_map);
								}
							}
							catch (Exception _e) {
								_e.printStackTrace();
							}
							parts.edit().putString("3", String.valueOf((long)(lm.size()))).commit();
							Collections.reverse(lm);
							listview.setAdapter(new ListviewAdapter(lm));
						}
						@Override
						public void onCancelled(DatabaseError _databaseError) {
						}
					});
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (getIntent().getStringExtra("part").equals("3")) {
					msg3.addListenerForSingleValueEvent(new ValueEventListener() {
						@Override
						public void onDataChange(DataSnapshot _dataSnapshot) {
							lm = new ArrayList<>();
							try {
								GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								for (DataSnapshot _data : _dataSnapshot.getChildren()) {
									HashMap<String, Object> _map = _data.getValue(_ind);
									lm.add(_map);
								}
							}
							catch (Exception _e) {
								_e.printStackTrace();
							}
							parts.edit().putString("3", String.valueOf((long)(lm.size()))).commit();
							Collections.reverse(lm);
							listview.setAdapter(new ListviewAdapter(lm));
						}
						@Override
						public void onCancelled(DatabaseError _databaseError) {
						}
					});
				}
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (getIntent().getStringExtra("part").equals("3")) {
					msg3.addListenerForSingleValueEvent(new ValueEventListener() {
						@Override
						public void onDataChange(DataSnapshot _dataSnapshot) {
							lm = new ArrayList<>();
							try {
								GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								for (DataSnapshot _data : _dataSnapshot.getChildren()) {
									HashMap<String, Object> _map = _data.getValue(_ind);
									lm.add(_map);
								}
							}
							catch (Exception _e) {
								_e.printStackTrace();
							}
							parts.edit().putString("3", String.valueOf((long)(lm.size()))).commit();
							Collections.reverse(lm);
							listview.setAdapter(new ListviewAdapter(lm));
						}
						@Override
						public void onCancelled(DatabaseError _databaseError) {
						}
					});
				}
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
				if (getIntent().getStringExtra("part").equals("4")) {
					msg4.addListenerForSingleValueEvent(new ValueEventListener() {
						@Override
						public void onDataChange(DataSnapshot _dataSnapshot) {
							lm = new ArrayList<>();
							try {
								GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								for (DataSnapshot _data : _dataSnapshot.getChildren()) {
									HashMap<String, Object> _map = _data.getValue(_ind);
									lm.add(_map);
								}
							}
							catch (Exception _e) {
								_e.printStackTrace();
							}
							parts.edit().putString("4", String.valueOf((long)(lm.size()))).commit();
							Collections.reverse(lm);
							listview.setAdapter(new ListviewAdapter(lm));
						}
						@Override
						public void onCancelled(DatabaseError _databaseError) {
						}
					});
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (getIntent().getStringExtra("part").equals("4")) {
					msg4.addListenerForSingleValueEvent(new ValueEventListener() {
						@Override
						public void onDataChange(DataSnapshot _dataSnapshot) {
							lm = new ArrayList<>();
							try {
								GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								for (DataSnapshot _data : _dataSnapshot.getChildren()) {
									HashMap<String, Object> _map = _data.getValue(_ind);
									lm.add(_map);
								}
							}
							catch (Exception _e) {
								_e.printStackTrace();
							}
							parts.edit().putString("4", String.valueOf((long)(lm.size()))).commit();
							Collections.reverse(lm);
							listview.setAdapter(new ListviewAdapter(lm));
						}
						@Override
						public void onCancelled(DatabaseError _databaseError) {
						}
					});
				}
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (getIntent().getStringExtra("part").equals("4")) {
					msg4.addListenerForSingleValueEvent(new ValueEventListener() {
						@Override
						public void onDataChange(DataSnapshot _dataSnapshot) {
							lm = new ArrayList<>();
							try {
								GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								for (DataSnapshot _data : _dataSnapshot.getChildren()) {
									HashMap<String, Object> _map = _data.getValue(_ind);
									lm.add(_map);
								}
							}
							catch (Exception _e) {
								_e.printStackTrace();
							}
							parts.edit().putString("4", String.valueOf((long)(lm.size()))).commit();
							Collections.reverse(lm);
							listview.setAdapter(new ListviewAdapter(lm));
						}
						@Override
						public void onCancelled(DatabaseError _databaseError) {
						}
					});
				}
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
				if (getIntent().getStringExtra("part").equals("5")) {
					msg5.addListenerForSingleValueEvent(new ValueEventListener() {
						@Override
						public void onDataChange(DataSnapshot _dataSnapshot) {
							lm = new ArrayList<>();
							try {
								GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								for (DataSnapshot _data : _dataSnapshot.getChildren()) {
									HashMap<String, Object> _map = _data.getValue(_ind);
									lm.add(_map);
								}
							}
							catch (Exception _e) {
								_e.printStackTrace();
							}
							parts.edit().putString("5", String.valueOf((long)(lm.size()))).commit();
							Collections.reverse(lm);
							listview.setAdapter(new ListviewAdapter(lm));
						}
						@Override
						public void onCancelled(DatabaseError _databaseError) {
						}
					});
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (getIntent().getStringExtra("part").equals("5")) {
					msg5.addListenerForSingleValueEvent(new ValueEventListener() {
						@Override
						public void onDataChange(DataSnapshot _dataSnapshot) {
							lm = new ArrayList<>();
							try {
								GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								for (DataSnapshot _data : _dataSnapshot.getChildren()) {
									HashMap<String, Object> _map = _data.getValue(_ind);
									lm.add(_map);
								}
							}
							catch (Exception _e) {
								_e.printStackTrace();
							}
							parts.edit().putString("5", String.valueOf((long)(lm.size()))).commit();
							Collections.reverse(lm);
							listview.setAdapter(new ListviewAdapter(lm));
						}
						@Override
						public void onCancelled(DatabaseError _databaseError) {
						}
					});
				}
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (getIntent().getStringExtra("part").equals("5")) {
					msg5.addListenerForSingleValueEvent(new ValueEventListener() {
						@Override
						public void onDataChange(DataSnapshot _dataSnapshot) {
							lm = new ArrayList<>();
							try {
								GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								for (DataSnapshot _data : _dataSnapshot.getChildren()) {
									HashMap<String, Object> _map = _data.getValue(_ind);
									lm.add(_map);
								}
							}
							catch (Exception _e) {
								_e.printStackTrace();
							}
							parts.edit().putString("5", String.valueOf((long)(lm.size()))).commit();
							Collections.reverse(lm);
							listview.setAdapter(new ListviewAdapter(lm));
						}
						@Override
						public void onCancelled(DatabaseError _databaseError) {
						}
					});
				}
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
				if (getIntent().getStringExtra("part").equals("6")) {
					msg6.addListenerForSingleValueEvent(new ValueEventListener() {
						@Override
						public void onDataChange(DataSnapshot _dataSnapshot) {
							lm = new ArrayList<>();
							try {
								GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								for (DataSnapshot _data : _dataSnapshot.getChildren()) {
									HashMap<String, Object> _map = _data.getValue(_ind);
									lm.add(_map);
								}
							}
							catch (Exception _e) {
								_e.printStackTrace();
							}
							parts.edit().putString("6", String.valueOf((long)(lm.size()))).commit();
							Collections.reverse(lm);
							listview.setAdapter(new ListviewAdapter(lm));
						}
						@Override
						public void onCancelled(DatabaseError _databaseError) {
						}
					});
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (getIntent().getStringExtra("part").equals("6")) {
					msg6.addListenerForSingleValueEvent(new ValueEventListener() {
						@Override
						public void onDataChange(DataSnapshot _dataSnapshot) {
							lm = new ArrayList<>();
							try {
								GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								for (DataSnapshot _data : _dataSnapshot.getChildren()) {
									HashMap<String, Object> _map = _data.getValue(_ind);
									lm.add(_map);
								}
							}
							catch (Exception _e) {
								_e.printStackTrace();
							}
							parts.edit().putString("6", String.valueOf((long)(lm.size()))).commit();
							Collections.reverse(lm);
							listview.setAdapter(new ListviewAdapter(lm));
						}
						@Override
						public void onCancelled(DatabaseError _databaseError) {
						}
					});
				}
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (getIntent().getStringExtra("part").equals("6")) {
					msg6.addListenerForSingleValueEvent(new ValueEventListener() {
						@Override
						public void onDataChange(DataSnapshot _dataSnapshot) {
							lm = new ArrayList<>();
							try {
								GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								for (DataSnapshot _data : _dataSnapshot.getChildren()) {
									HashMap<String, Object> _map = _data.getValue(_ind);
									lm.add(_map);
								}
							}
							catch (Exception _e) {
								_e.printStackTrace();
							}
							parts.edit().putString("6", String.valueOf((long)(lm.size()))).commit();
							Collections.reverse(lm);
							listview.setAdapter(new ListviewAdapter(lm));
						}
						@Override
						public void onCancelled(DatabaseError _databaseError) {
						}
					});
				}
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
				if (getIntent().getStringExtra("part").equals("7")) {
					msg7.addListenerForSingleValueEvent(new ValueEventListener() {
						@Override
						public void onDataChange(DataSnapshot _dataSnapshot) {
							lm = new ArrayList<>();
							try {
								GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								for (DataSnapshot _data : _dataSnapshot.getChildren()) {
									HashMap<String, Object> _map = _data.getValue(_ind);
									lm.add(_map);
								}
							}
							catch (Exception _e) {
								_e.printStackTrace();
							}
							parts.edit().putString("7", String.valueOf((long)(lm.size()))).commit();
							Collections.reverse(lm);
							listview.setAdapter(new ListviewAdapter(lm));
						}
						@Override
						public void onCancelled(DatabaseError _databaseError) {
						}
					});
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (getIntent().getStringExtra("part").equals("7")) {
					msg7.addListenerForSingleValueEvent(new ValueEventListener() {
						@Override
						public void onDataChange(DataSnapshot _dataSnapshot) {
							lm = new ArrayList<>();
							try {
								GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								for (DataSnapshot _data : _dataSnapshot.getChildren()) {
									HashMap<String, Object> _map = _data.getValue(_ind);
									lm.add(_map);
								}
							}
							catch (Exception _e) {
								_e.printStackTrace();
							}
							parts.edit().putString("7", String.valueOf((long)(lm.size()))).commit();
							Collections.reverse(lm);
							listview.setAdapter(new ListviewAdapter(lm));
						}
						@Override
						public void onCancelled(DatabaseError _databaseError) {
						}
					});
				}
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (getIntent().getStringExtra("part").equals("7")) {
					msg7.addListenerForSingleValueEvent(new ValueEventListener() {
						@Override
						public void onDataChange(DataSnapshot _dataSnapshot) {
							lm = new ArrayList<>();
							try {
								GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								for (DataSnapshot _data : _dataSnapshot.getChildren()) {
									HashMap<String, Object> _map = _data.getValue(_ind);
									lm.add(_map);
								}
							}
							catch (Exception _e) {
								_e.printStackTrace();
							}
							parts.edit().putString("7", String.valueOf((long)(lm.size()))).commit();
							Collections.reverse(lm);
							listview.setAdapter(new ListviewAdapter(lm));
						}
						@Override
						public void onCancelled(DatabaseError _databaseError) {
						}
					});
				}
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
				if (getIntent().getStringExtra("part").equals("8")) {
					msg8.addListenerForSingleValueEvent(new ValueEventListener() {
						@Override
						public void onDataChange(DataSnapshot _dataSnapshot) {
							lm = new ArrayList<>();
							try {
								GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								for (DataSnapshot _data : _dataSnapshot.getChildren()) {
									HashMap<String, Object> _map = _data.getValue(_ind);
									lm.add(_map);
								}
							}
							catch (Exception _e) {
								_e.printStackTrace();
							}
							parts.edit().putString("8", String.valueOf((long)(lm.size()))).commit();
							Collections.reverse(lm);
							listview.setAdapter(new ListviewAdapter(lm));
						}
						@Override
						public void onCancelled(DatabaseError _databaseError) {
						}
					});
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (getIntent().getStringExtra("part").equals("8")) {
					msg8.addListenerForSingleValueEvent(new ValueEventListener() {
						@Override
						public void onDataChange(DataSnapshot _dataSnapshot) {
							lm = new ArrayList<>();
							try {
								GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								for (DataSnapshot _data : _dataSnapshot.getChildren()) {
									HashMap<String, Object> _map = _data.getValue(_ind);
									lm.add(_map);
								}
							}
							catch (Exception _e) {
								_e.printStackTrace();
							}
							parts.edit().putString("8", String.valueOf((long)(lm.size()))).commit();
							Collections.reverse(lm);
							listview.setAdapter(new ListviewAdapter(lm));
						}
						@Override
						public void onCancelled(DatabaseError _databaseError) {
						}
					});
				}
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (getIntent().getStringExtra("part").equals("8")) {
					msg8.addListenerForSingleValueEvent(new ValueEventListener() {
						@Override
						public void onDataChange(DataSnapshot _dataSnapshot) {
							lm = new ArrayList<>();
							try {
								GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								for (DataSnapshot _data : _dataSnapshot.getChildren()) {
									HashMap<String, Object> _map = _data.getValue(_ind);
									lm.add(_map);
								}
							}
							catch (Exception _e) {
								_e.printStackTrace();
							}
							parts.edit().putString("8", String.valueOf((long)(lm.size()))).commit();
							Collections.reverse(lm);
							listview.setAdapter(new ListviewAdapter(lm));
						}
						@Override
						public void onCancelled(DatabaseError _databaseError) {
						}
					});
				}
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
				if (getIntent().getStringExtra("part").equals("9")) {
					msg9.addListenerForSingleValueEvent(new ValueEventListener() {
						@Override
						public void onDataChange(DataSnapshot _dataSnapshot) {
							lm = new ArrayList<>();
							try {
								GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								for (DataSnapshot _data : _dataSnapshot.getChildren()) {
									HashMap<String, Object> _map = _data.getValue(_ind);
									lm.add(_map);
								}
							}
							catch (Exception _e) {
								_e.printStackTrace();
							}
							parts.edit().putString("9", String.valueOf((long)(lm.size()))).commit();
							Collections.reverse(lm);
							listview.setAdapter(new ListviewAdapter(lm));
						}
						@Override
						public void onCancelled(DatabaseError _databaseError) {
						}
					});
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (getIntent().getStringExtra("part").equals("9")) {
					msg9.addListenerForSingleValueEvent(new ValueEventListener() {
						@Override
						public void onDataChange(DataSnapshot _dataSnapshot) {
							lm = new ArrayList<>();
							try {
								GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								for (DataSnapshot _data : _dataSnapshot.getChildren()) {
									HashMap<String, Object> _map = _data.getValue(_ind);
									lm.add(_map);
								}
							}
							catch (Exception _e) {
								_e.printStackTrace();
							}
							parts.edit().putString("9", String.valueOf((long)(lm.size()))).commit();
							Collections.reverse(lm);
							listview.setAdapter(new ListviewAdapter(lm));
						}
						@Override
						public void onCancelled(DatabaseError _databaseError) {
						}
					});
				}
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (getIntent().getStringExtra("part").equals("9")) {
					msg9.addListenerForSingleValueEvent(new ValueEventListener() {
						@Override
						public void onDataChange(DataSnapshot _dataSnapshot) {
							lm = new ArrayList<>();
							try {
								GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								for (DataSnapshot _data : _dataSnapshot.getChildren()) {
									HashMap<String, Object> _map = _data.getValue(_ind);
									lm.add(_map);
								}
							}
							catch (Exception _e) {
								_e.printStackTrace();
							}
							parts.edit().putString("9", String.valueOf((long)(lm.size()))).commit();
							Collections.reverse(lm);
							listview.setAdapter(new ListviewAdapter(lm));
						}
						@Override
						public void onCancelled(DatabaseError _databaseError) {
						}
					});
				}
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
				if (getIntent().getStringExtra("part").equals("10")) {
					msg10.addListenerForSingleValueEvent(new ValueEventListener() {
						@Override
						public void onDataChange(DataSnapshot _dataSnapshot) {
							lm = new ArrayList<>();
							try {
								GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								for (DataSnapshot _data : _dataSnapshot.getChildren()) {
									HashMap<String, Object> _map = _data.getValue(_ind);
									lm.add(_map);
								}
							}
							catch (Exception _e) {
								_e.printStackTrace();
							}
							parts.edit().putString("10", String.valueOf((long)(lm.size()))).commit();
							Collections.reverse(lm);
							listview.setAdapter(new ListviewAdapter(lm));
						}
						@Override
						public void onCancelled(DatabaseError _databaseError) {
						}
					});
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (getIntent().getStringExtra("part").equals("10")) {
					msg10.addListenerForSingleValueEvent(new ValueEventListener() {
						@Override
						public void onDataChange(DataSnapshot _dataSnapshot) {
							lm = new ArrayList<>();
							try {
								GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								for (DataSnapshot _data : _dataSnapshot.getChildren()) {
									HashMap<String, Object> _map = _data.getValue(_ind);
									lm.add(_map);
								}
							}
							catch (Exception _e) {
								_e.printStackTrace();
							}
							parts.edit().putString("10", String.valueOf((long)(lm.size()))).commit();
							Collections.reverse(lm);
							listview.setAdapter(new ListviewAdapter(lm));
						}
						@Override
						public void onCancelled(DatabaseError _databaseError) {
						}
					});
				}
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (getIntent().getStringExtra("part").equals("10")) {
					msg10.addListenerForSingleValueEvent(new ValueEventListener() {
						@Override
						public void onDataChange(DataSnapshot _dataSnapshot) {
							lm = new ArrayList<>();
							try {
								GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								for (DataSnapshot _data : _dataSnapshot.getChildren()) {
									HashMap<String, Object> _map = _data.getValue(_ind);
									lm.add(_map);
								}
							}
							catch (Exception _e) {
								_e.printStackTrace();
							}
							parts.edit().putString("10", String.valueOf((long)(lm.size()))).commit();
							Collections.reverse(lm);
							listview.setAdapter(new ListviewAdapter(lm));
						}
						@Override
						public void onCancelled(DatabaseError _databaseError) {
						}
					});
				}
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
				if (getIntent().getStringExtra("part").equals("11")) {
					msg11.addListenerForSingleValueEvent(new ValueEventListener() {
						@Override
						public void onDataChange(DataSnapshot _dataSnapshot) {
							lm = new ArrayList<>();
							try {
								GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								for (DataSnapshot _data : _dataSnapshot.getChildren()) {
									HashMap<String, Object> _map = _data.getValue(_ind);
									lm.add(_map);
								}
							}
							catch (Exception _e) {
								_e.printStackTrace();
							}
							parts.edit().putString("11", String.valueOf((long)(lm.size()))).commit();
							Collections.reverse(lm);
							listview.setAdapter(new ListviewAdapter(lm));
						}
						@Override
						public void onCancelled(DatabaseError _databaseError) {
						}
					});
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (getIntent().getStringExtra("part").equals("11")) {
					msg11.addListenerForSingleValueEvent(new ValueEventListener() {
						@Override
						public void onDataChange(DataSnapshot _dataSnapshot) {
							lm = new ArrayList<>();
							try {
								GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								for (DataSnapshot _data : _dataSnapshot.getChildren()) {
									HashMap<String, Object> _map = _data.getValue(_ind);
									lm.add(_map);
								}
							}
							catch (Exception _e) {
								_e.printStackTrace();
							}
							parts.edit().putString("11", String.valueOf((long)(lm.size()))).commit();
							Collections.reverse(lm);
							listview.setAdapter(new ListviewAdapter(lm));
						}
						@Override
						public void onCancelled(DatabaseError _databaseError) {
						}
					});
				}
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (getIntent().getStringExtra("part").equals("11")) {
					msg11.addListenerForSingleValueEvent(new ValueEventListener() {
						@Override
						public void onDataChange(DataSnapshot _dataSnapshot) {
							lm = new ArrayList<>();
							try {
								GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								for (DataSnapshot _data : _dataSnapshot.getChildren()) {
									HashMap<String, Object> _map = _data.getValue(_ind);
									lm.add(_map);
								}
							}
							catch (Exception _e) {
								_e.printStackTrace();
							}
							parts.edit().putString("11", String.valueOf((long)(lm.size()))).commit();
							Collections.reverse(lm);
							listview.setAdapter(new ListviewAdapter(lm));
						}
						@Override
						public void onCancelled(DatabaseError _databaseError) {
						}
					});
				}
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
		listview.setHorizontalScrollBarEnabled(false);
		listview.setVerticalScrollBarEnabled(false);
		listview.setOverScrollMode(ListView.OVER_SCROLL_NEVER);
		linear4.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)35, 0xFF37474F));
		linear5.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)35, 0xFF37474F));
		linear7.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)35, 0xFF37474F));
		edittext1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF455A64));
		imageview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF455A64));
		imageview3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF455A64));
		progressbar1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF455A64));
		textview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF455A64));
		linear5.setVisibility(View.GONE);
		progressbar1.setVisibility(View.GONE);
		linear7.setVisibility(View.GONE);
		imgshow = 0;
		path = "";
		if (getIntent().getStringExtra("part").equals("2") || (getIntent().getStringExtra("part").equals("3") || getIntent().getStringExtra("part").equals("11"))) {
			if (!login.getString("name", "").contains("🌟")) {
				linear4.setVisibility(View.GONE);
				linear7.setVisibility(View.VISIBLE);
			}
		}
		if (login.getString("text", "").length() > 0) {
			edittext1.setText(login.getString("text", ""));
		}
		if (getIntent().getStringExtra("part").equals("1")) {
			msg.addListenerForSingleValueEvent(new ValueEventListener() {
				@Override
				public void onDataChange(DataSnapshot _dataSnapshot) {
					lm = new ArrayList<>();
					try {
						GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
						for (DataSnapshot _data : _dataSnapshot.getChildren()) {
							HashMap<String, Object> _map = _data.getValue(_ind);
							lm.add(_map);
						}
					}
					catch (Exception _e) {
						_e.printStackTrace();
					}
					Collections.reverse(lm);
					listview.setAdapter(new ListviewAdapter(lm));
				}
				@Override
				public void onCancelled(DatabaseError _databaseError) {
				}
			});
		}
		else {
			if (getIntent().getStringExtra("part").equals("2")) {
				msg2.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						lm = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								lm.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						Collections.reverse(lm);
						listview.setAdapter(new ListviewAdapter(lm));
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
			else {
				if (getIntent().getStringExtra("part").equals("3")) {
					msg3.addListenerForSingleValueEvent(new ValueEventListener() {
						@Override
						public void onDataChange(DataSnapshot _dataSnapshot) {
							lm = new ArrayList<>();
							try {
								GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								for (DataSnapshot _data : _dataSnapshot.getChildren()) {
									HashMap<String, Object> _map = _data.getValue(_ind);
									lm.add(_map);
								}
							}
							catch (Exception _e) {
								_e.printStackTrace();
							}
							Collections.reverse(lm);
							listview.setAdapter(new ListviewAdapter(lm));
						}
						@Override
						public void onCancelled(DatabaseError _databaseError) {
						}
					});
				}
				else {
					if (getIntent().getStringExtra("part").equals("4")) {
						msg4.addListenerForSingleValueEvent(new ValueEventListener() {
							@Override
							public void onDataChange(DataSnapshot _dataSnapshot) {
								lm = new ArrayList<>();
								try {
									GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
									for (DataSnapshot _data : _dataSnapshot.getChildren()) {
										HashMap<String, Object> _map = _data.getValue(_ind);
										lm.add(_map);
									}
								}
								catch (Exception _e) {
									_e.printStackTrace();
								}
								Collections.reverse(lm);
								listview.setAdapter(new ListviewAdapter(lm));
							}
							@Override
							public void onCancelled(DatabaseError _databaseError) {
							}
						});
					}
					else {
						if (getIntent().getStringExtra("part").equals("5")) {
							msg5.addListenerForSingleValueEvent(new ValueEventListener() {
								@Override
								public void onDataChange(DataSnapshot _dataSnapshot) {
									lm = new ArrayList<>();
									try {
										GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
										for (DataSnapshot _data : _dataSnapshot.getChildren()) {
											HashMap<String, Object> _map = _data.getValue(_ind);
											lm.add(_map);
										}
									}
									catch (Exception _e) {
										_e.printStackTrace();
									}
									Collections.reverse(lm);
									listview.setAdapter(new ListviewAdapter(lm));
								}
								@Override
								public void onCancelled(DatabaseError _databaseError) {
								}
							});
						}
						else {
							if (getIntent().getStringExtra("part").equals("6")) {
								msg6.addListenerForSingleValueEvent(new ValueEventListener() {
									@Override
									public void onDataChange(DataSnapshot _dataSnapshot) {
										lm = new ArrayList<>();
										try {
											GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
											for (DataSnapshot _data : _dataSnapshot.getChildren()) {
												HashMap<String, Object> _map = _data.getValue(_ind);
												lm.add(_map);
											}
										}
										catch (Exception _e) {
											_e.printStackTrace();
										}
										Collections.reverse(lm);
										listview.setAdapter(new ListviewAdapter(lm));
									}
									@Override
									public void onCancelled(DatabaseError _databaseError) {
									}
								});
							}
							else {
								if (getIntent().getStringExtra("part").equals("7")) {
									msg7.addListenerForSingleValueEvent(new ValueEventListener() {
										@Override
										public void onDataChange(DataSnapshot _dataSnapshot) {
											lm = new ArrayList<>();
											try {
												GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
												for (DataSnapshot _data : _dataSnapshot.getChildren()) {
													HashMap<String, Object> _map = _data.getValue(_ind);
													lm.add(_map);
												}
											}
											catch (Exception _e) {
												_e.printStackTrace();
											}
											Collections.reverse(lm);
											listview.setAdapter(new ListviewAdapter(lm));
										}
										@Override
										public void onCancelled(DatabaseError _databaseError) {
										}
									});
								}
								else {
									if (getIntent().getStringExtra("part").equals("8")) {
										msg8.addListenerForSingleValueEvent(new ValueEventListener() {
											@Override
											public void onDataChange(DataSnapshot _dataSnapshot) {
												lm = new ArrayList<>();
												try {
													GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
													for (DataSnapshot _data : _dataSnapshot.getChildren()) {
														HashMap<String, Object> _map = _data.getValue(_ind);
														lm.add(_map);
													}
												}
												catch (Exception _e) {
													_e.printStackTrace();
												}
												Collections.reverse(lm);
												listview.setAdapter(new ListviewAdapter(lm));
											}
											@Override
											public void onCancelled(DatabaseError _databaseError) {
											}
										});
									}
									else {
										if (getIntent().getStringExtra("part").equals("9")) {
											msg9.addListenerForSingleValueEvent(new ValueEventListener() {
												@Override
												public void onDataChange(DataSnapshot _dataSnapshot) {
													lm = new ArrayList<>();
													try {
														GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
														for (DataSnapshot _data : _dataSnapshot.getChildren()) {
															HashMap<String, Object> _map = _data.getValue(_ind);
															lm.add(_map);
														}
													}
													catch (Exception _e) {
														_e.printStackTrace();
													}
													Collections.reverse(lm);
													listview.setAdapter(new ListviewAdapter(lm));
												}
												@Override
												public void onCancelled(DatabaseError _databaseError) {
												}
											});
										}
										else {
											if (getIntent().getStringExtra("part").equals("10")) {
												msg10.addListenerForSingleValueEvent(new ValueEventListener() {
													@Override
													public void onDataChange(DataSnapshot _dataSnapshot) {
														lm = new ArrayList<>();
														try {
															GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
															for (DataSnapshot _data : _dataSnapshot.getChildren()) {
																HashMap<String, Object> _map = _data.getValue(_ind);
																lm.add(_map);
															}
														}
														catch (Exception _e) {
															_e.printStackTrace();
														}
														Collections.reverse(lm);
														listview.setAdapter(new ListviewAdapter(lm));
													}
													@Override
													public void onCancelled(DatabaseError _databaseError) {
													}
												});
											}
											else {
												if (getIntent().getStringExtra("part").equals("11")) {
													msg11.addListenerForSingleValueEvent(new ValueEventListener() {
														@Override
														public void onDataChange(DataSnapshot _dataSnapshot) {
															lm = new ArrayList<>();
															try {
																GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
																for (DataSnapshot _data : _dataSnapshot.getChildren()) {
																	HashMap<String, Object> _map = _data.getValue(_ind);
																	lm.add(_map);
																}
															}
															catch (Exception _e) {
																_e.printStackTrace();
															}
															Collections.reverse(lm);
															listview.setAdapter(new ListviewAdapter(lm));
														}
														@Override
														public void onCancelled(DatabaseError _databaseError) {
														}
													});
												}
												else {
													
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		switch (_requestCode) {
			case REQ_CD_FP:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				imageview6.setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
				imageview6.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(_filePath.get((int)(0)), 1024, 1024));
				path = _filePath.get((int)(0));
			}
			else {
				
			}
			break;
			default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		i.setClass(getApplicationContext(), PartsActivity.class);
		startActivity(i);
		finish();
	}
	public void _light () {
		d = new AlertDialog.Builder(this,AlertDialog.THEME_DEVICE_DEFAULT_DARK);
		d.setCancelable(true);
	}
	
	
	public void _extra () {
	}
	public static final class AESCrypt {
		    private static final String TAG = "AESCrypt";
		    private static final String AES_MODE = "AES/CBC/PKCS7Padding";
		    private static final String CHARSET = "UTF-8";
		    private static final String HASH_ALGORITHM = "SHA-256";
		    private static final byte[] ivBytes = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
		    public static boolean DEBUG_LOG_ENABLED = false;
		    private static javax.crypto.spec.SecretKeySpec generateKey(final String password) throws java.security.NoSuchAlgorithmException, java.io.UnsupportedEncodingException {
			        final java.security.MessageDigest digest = java.security.MessageDigest.getInstance(HASH_ALGORITHM);
			        byte[] bytes = password.getBytes("UTF-8");
			        digest.update(bytes, 0, bytes.length);
			        byte[] key = digest.digest();
			        log("SHA-256 key ", key);
			        javax.crypto.spec.SecretKeySpec secretKeySpec = new javax.crypto.spec.SecretKeySpec(key, "AES");
			        return secretKeySpec;
			    }
		    public static String encrypt(final String password, String message)
		            throws java.security.GeneralSecurityException {
			        try {
				            final javax.crypto.spec.SecretKeySpec key = generateKey(password);
				            log("message", message);
				            byte[] cipherText = encrypt(key, ivBytes, message.getBytes(CHARSET));
				            String encoded = android.util.Base64.encodeToString(cipherText, android.util.Base64.NO_WRAP);
				            log("Base64.NO_WRAP", encoded);
				            return encoded;
				        } catch (java.io.UnsupportedEncodingException e) {
				            if (DEBUG_LOG_ENABLED)
				                android.util.Log.e(TAG, "UnsupportedEncodingException ", e);
				            throw new java.security.GeneralSecurityException(e);
				        }
			    }
		    public static byte[] encrypt(final javax.crypto.spec.SecretKeySpec key, final byte[] iv, final byte[] message)
		            throws java.security.GeneralSecurityException {
			        final javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance(AES_MODE);
			        javax.crypto.spec.IvParameterSpec ivSpec = new javax.crypto.spec.IvParameterSpec(iv);
			        cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, key, ivSpec);
			        byte[] cipherText = cipher.doFinal(message);
			        log("cipherText", cipherText);
			        return cipherText;
			    }
		    public static String decrypt(final String password, String base64EncodedCipherText)
		            throws java.security.GeneralSecurityException {
			        try {
				            final javax.crypto.spec.SecretKeySpec key = generateKey(password);
				            log("base64EncodedCipherText", base64EncodedCipherText);
				            byte[] decodedCipherText = android.util.Base64.decode(base64EncodedCipherText, android.util.Base64.NO_WRAP);
				            log("decodedCipherText", decodedCipherText);
				            byte[] decryptedBytes = decrypt(key, ivBytes, decodedCipherText);
				            log("decryptedBytes", decryptedBytes);
				            String message = new String(decryptedBytes, CHARSET);
				            log("message", message);
				            return message;
				        } catch (java.io.UnsupportedEncodingException e) {
				            if (DEBUG_LOG_ENABLED)
				                android.util.Log.e(TAG, "UnsupportedEncodingException ", e);
				            throw new java.security.GeneralSecurityException(e);
				        }
			    }
		    public static byte[] decrypt(final javax.crypto.spec.SecretKeySpec key, final byte[] iv, final byte[] decodedCipherText)
		            throws java.security.GeneralSecurityException {
			            final javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance(AES_MODE);
			            javax.crypto.spec.IvParameterSpec ivSpec = new javax.crypto.spec.IvParameterSpec(iv);
			            cipher.init(javax.crypto.Cipher.DECRYPT_MODE, key, ivSpec);
			            byte[] decryptedBytes = cipher.doFinal(decodedCipherText);
			            log("decryptedBytes", decryptedBytes);
			            return decryptedBytes;
			    }
		    private static void log(String what, byte[] bytes) {
			        if (DEBUG_LOG_ENABLED)
			            android.util.Log.d(TAG, what + "[" + bytes.length + "] [" + bytesToHex(bytes) + "]");
			    }
		    private static void log(String what, String value) {
			        if (DEBUG_LOG_ENABLED)
			            android.util.Log.d(TAG, what + "[" + value.length() + "] [" + value + "]");
			    }
		    private static String bytesToHex(byte[] bytes) {
			        final char[] hexArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
				                '9', 'A', 'B', 'C', 'D', 'E', 'F'};
			        char[] hexChars = new char[bytes.length * 2];
			        int v;
			        for (int j = 0; j < bytes.length; j++) {
				            v = bytes[j] & 0xFF;
				            hexChars[j * 2] = hexArray[v >>> 4];
				            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
				        }
			        return new String(hexChars);
			    }
		    private AESCrypt() {
			    }
	}
	private String cryptedOutput;
	private String decryptedOutput; 
	
	public void encryptText(String text, String key){
		  
		try {    	
			 cryptedOutput =  AESCrypt.encrypt(key, text);
		}catch (java.security.GeneralSecurityException e){
				showMessage("password not correct !" + "\n" + e.toString());
		}
	}
	
	public void decryptCode(String code, String key){
		  
		  try {
			  decryptedOutput =	AESCrypt.decrypt(key, code);
			  } catch (java.security.GeneralSecurityException e){
			      showMessage("password incorrect !" + "\n" + e.toString());
			  }
	}
	{
	}
	
	
	public class ListviewAdapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public ListviewAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.cell, null);
			}
			
			final LinearLayout form1 = (LinearLayout) _view.findViewById(R.id.form1);
			final LinearLayout form2 = (LinearLayout) _view.findViewById(R.id.form2);
			final LinearLayout linear5 = (LinearLayout) _view.findViewById(R.id.linear5);
			final LinearLayout linear2 = (LinearLayout) _view.findViewById(R.id.linear2);
			final TextView msgcell1 = (TextView) _view.findViewById(R.id.msgcell1);
			final ImageView pic1 = (ImageView) _view.findViewById(R.id.pic1);
			final TextView date1 = (TextView) _view.findViewById(R.id.date1);
			final de.hdodenhof.circleimageview.CircleImageView circleimg1 = (de.hdodenhof.circleimageview.CircleImageView) _view.findViewById(R.id.circleimg1);
			final TextView name1 = (TextView) _view.findViewById(R.id.name1);
			final ImageView more1 = (ImageView) _view.findViewById(R.id.more1);
			final LinearLayout linear4 = (LinearLayout) _view.findViewById(R.id.linear4);
			final TextView msgcell2 = (TextView) _view.findViewById(R.id.msgcell2);
			final ImageView pic2 = (ImageView) _view.findViewById(R.id.pic2);
			final TextView date2 = (TextView) _view.findViewById(R.id.date2);
			final ImageView more2 = (ImageView) _view.findViewById(R.id.more2);
			final TextView name2 = (TextView) _view.findViewById(R.id.name2);
			final de.hdodenhof.circleimageview.CircleImageView circleimg2 = (de.hdodenhof.circleimageview.CircleImageView) _view.findViewById(R.id.circleimg2);
			
			  try {
				  if (_position == (lm.size() - 1)) {
					linear5.setVisibility(View.VISIBLE);
				}
				else {
					linear5.setVisibility(View.GONE);
				}
				if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("name").toString()).equals(login.getString("name", ""))) {
					form1.setVisibility(View.GONE);
					form2.setVisibility(View.VISIBLE);
					pic2.setVisibility(View.GONE);
					msgcell2.setVisibility(View.GONE);
					if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("name").toString()).contains("🌟")) {
						form2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)35, (int)1, 0xFFFFC107, 0xFF37474F));
						more2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF455A64));
					}
					else {
						form2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)35, 0xFF37474F));
						more2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF455A64));
					}
					name2.setText(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("name").toString()));
					date2.setText(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("time").toString()));
					if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("msg").toString()).length() > 0) {
						msgcell2.setVisibility(View.VISIBLE);
						msgcell2.setText(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("msg").toString()));
						msgcell2.setClickable(true);
						android.text.util.Linkify.addLinks(msgcell2, android.text.util.Linkify.ALL);
						msgcell2.setLinkTextColor(Color.parseColor("#FF008CFF"));
						msgcell2.setLinksClickable(true);
					}
					if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
						pic2.setVisibility(View.VISIBLE);
						Glide.with(getApplicationContext()).load(Uri.parse(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()))).into(pic2);
					}
					if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("img").toString()).length() > 0) {
						Glide.with(getApplicationContext()).load(Uri.parse(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("img").toString()))).into(circleimg2);
					}
				}
				else {
					form2.setVisibility(View.GONE);
					form1.setVisibility(View.VISIBLE);
					pic1.setVisibility(View.GONE);
					msgcell1.setVisibility(View.GONE);
					if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("name").toString()).contains("🌟")) {
						form1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)35, (int)1, 0xFFFFC107, 0xFF37474F));
						more1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF455A64));
					}
					else {
						form1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)35, 0xFF37474F));
						more1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF455A64));
					}
					name1.setText(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("name").toString()));
					date1.setText(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("time").toString()));
					if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("msg").toString()).length() > 0) {
						msgcell1.setVisibility(View.VISIBLE);
						msgcell1.setText(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("msg").toString()));
						msgcell1.setClickable(true);
						android.text.util.Linkify.addLinks(msgcell1, android.text.util.Linkify.ALL);
						msgcell1.setLinkTextColor(Color.parseColor("#FF008CFF"));
						msgcell1.setLinksClickable(true);
					}
					if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
						pic1.setVisibility(View.VISIBLE);
						Glide.with(getApplicationContext()).load(Uri.parse(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()))).into(pic1);
					}
					if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("img").toString()).length() > 0) {
						Glide.with(getApplicationContext()).load(Uri.parse(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("img").toString()))).into(circleimg1);
					}
				}
				more1.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						if (login.getString("name", "").contains("🌟")) {
							_light();
							d.setTitle("ISNAD");
							d.setIcon(R.drawable.logo);
							d.setMessage("حدد ما تريد تنفيذه :");
							if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("msg").toString()).length() > 0) {
								d.setPositiveButton("نسخ الرسالة", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface _dialog, int _which) {
										((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("msg").toString())));
										SketchwareUtil.showMessage(getApplicationContext(), "تم نسخ الرسالة");
									}
								});
							}
							d.setNeutralButton("حذف الرسالة", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface _dialog, int _which) {
									if (getIntent().getStringExtra("part").equals("1")) {
										if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
											_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
											msg.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
										}
										else {
											msg.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
										}
									}
									else {
										if (getIntent().getStringExtra("part").equals("2")) {
											if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
												_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
												msg2.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
											}
											else {
												msg2.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
											}
										}
										else {
											if (getIntent().getStringExtra("part").equals("3")) {
												if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
													_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
													msg3.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
												}
												else {
													msg3.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
												}
											}
											else {
												if (getIntent().getStringExtra("part").equals("4")) {
													if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
														_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
														msg4.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
													}
													else {
														msg4.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
													}
												}
												else {
													if (getIntent().getStringExtra("part").equals("5")) {
														if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
															_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
															msg5.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
														}
														else {
															msg5.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
														}
													}
													else {
														if (getIntent().getStringExtra("part").equals("6")) {
															if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
																_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
																msg6.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
															}
															else {
																msg6.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
															}
														}
														else {
															if (getIntent().getStringExtra("part").equals("7")) {
																if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
																	_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
																	msg7.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																}
																else {
																	msg7.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																}
															}
															else {
																if (getIntent().getStringExtra("part").equals("8")) {
																	if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
																		_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
																		msg8.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																	}
																	else {
																		msg8.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																	}
																}
																else {
																	if (getIntent().getStringExtra("part").equals("9")) {
																		if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
																			_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
																			msg9.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																		}
																		else {
																			msg9.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																		}
																	}
																	else {
																		if (getIntent().getStringExtra("part").equals("10")) {
																			if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
																				_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
																				msg10.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																			}
																			else {
																				msg10.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																			}
																		}
																		else {
																			if (getIntent().getStringExtra("part").equals("11")) {
																				if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
																					_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
																					msg11.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																				}
																				else {
																					msg11.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																				}
																			}
																			else {
																				
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							});
							d.create().show();
						}
						else {
							if (login.getString("name", "").equals(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("name").toString()))) {
								_light();
								d.setTitle("ISNAD");
								d.setIcon(R.drawable.logo);
								d.setMessage("حدد ما تريد تنفيذه :");
								if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("msg").toString()).length() > 0) {
									d.setPositiveButton("نسخ الرسالة", new DialogInterface.OnClickListener() {
										@Override
										public void onClick(DialogInterface _dialog, int _which) {
											((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("msg").toString())));
											SketchwareUtil.showMessage(getApplicationContext(), "تم نسخ الرسالة");
										}
									});
								}
								d.setNeutralButton("حذف الرسالة", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface _dialog, int _which) {
										if (getIntent().getStringExtra("part").equals("1")) {
											if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
												_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
												msg.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
											}
											else {
												msg.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
											}
										}
										else {
											if (getIntent().getStringExtra("part").equals("2")) {
												if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
													_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
													msg2.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
												}
												else {
													msg2.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
												}
											}
											else {
												if (getIntent().getStringExtra("part").equals("3")) {
													if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
														_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
														msg3.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
													}
													else {
														msg3.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
													}
												}
												else {
													if (getIntent().getStringExtra("part").equals("4")) {
														if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
															_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
															msg4.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
														}
														else {
															msg4.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
														}
													}
													else {
														if (getIntent().getStringExtra("part").equals("5")) {
															if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
																_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
																msg5.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
															}
															else {
																msg5.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
															}
														}
														else {
															if (getIntent().getStringExtra("part").equals("6")) {
																if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
																	_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
																	msg6.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																}
																else {
																	msg6.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																}
															}
															else {
																if (getIntent().getStringExtra("part").equals("7")) {
																	if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
																		_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
																		msg7.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																	}
																	else {
																		msg7.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																	}
																}
																else {
																	if (getIntent().getStringExtra("part").equals("8")) {
																		if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
																			_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
																			msg8.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																		}
																		else {
																			msg8.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																		}
																	}
																	else {
																		if (getIntent().getStringExtra("part").equals("9")) {
																			if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
																				_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
																				msg9.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																			}
																			else {
																				msg9.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																			}
																		}
																		else {
																			if (getIntent().getStringExtra("part").equals("10")) {
																				if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
																					_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
																					msg10.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																				}
																				else {
																					msg10.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																				}
																			}
																			else {
																				if (getIntent().getStringExtra("part").equals("11")) {
																					if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
																						_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
																						msg11.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																					}
																					else {
																						msg11.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																					}
																				}
																				else {
																					
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								});
								d.create().show();
							}
							else {
								if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("msg").toString()).length() > 0) {
									_light();
									d.setTitle("ISNAD");
									d.setIcon(R.drawable.logo);
									d.setMessage("حدد ما تريد تنفيذه :");
									d.setPositiveButton("نسخ الرسالة", new DialogInterface.OnClickListener() {
										@Override
										public void onClick(DialogInterface _dialog, int _which) {
											((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("msg").toString())));
											SketchwareUtil.showMessage(getApplicationContext(), "تم نسخ الرسالة");
										}
									});
									d.create().show();
								}
							}
						}
					}
				});
				pic1.setOnLongClickListener(new View.OnLongClickListener() {
					@Override
					public boolean onLongClick(View _view) {
						if (!FileUtil.isExistFile(FileUtil.getExternalStorageDir().concat("/Download/"))) {
							FileUtil.makeDir(FileUtil.getExternalStorageDir().concat("/Download/"));
						}
						randomv = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
						uotrandom = "";
						for (int i = 0; i < 8; i++) {
							uotrandom = uotrandom + randomv.charAt(new java.util.Random().nextInt(randomv.length()));
						}
						_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).getFile(new File(FileUtil.getExternalStorageDir().concat("/Download/".concat(uotrandom.concat(".png"))))).addOnSuccessListener(_pic_download_success_listener).addOnFailureListener(_pic_failure_listener).addOnProgressListener(_pic_download_progress_listener);
						SketchwareUtil.showMessage(getApplicationContext(), "جارى تحميل الصورة...");
						return true;
					}
				});
				more2.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						if (login.getString("name", "").contains("🌟")) {
							_light();
							d.setTitle("ISNAD");
							d.setIcon(R.drawable.logo);
							d.setMessage("حدد ما تريد تنفيذه :");
							if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("msg").toString()).length() > 0) {
								d.setPositiveButton("نسخ الرسالة", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface _dialog, int _which) {
										((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("msg").toString())));
										SketchwareUtil.showMessage(getApplicationContext(), "تم نسخ الرسالة");
									}
								});
							}
							d.setNeutralButton("حذف الرسالة", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface _dialog, int _which) {
									if (getIntent().getStringExtra("part").equals("1")) {
										if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
											_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
											msg.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
										}
										else {
											msg.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
										}
									}
									else {
										if (getIntent().getStringExtra("part").equals("2")) {
											if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
												_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
												msg2.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
											}
											else {
												msg2.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
											}
										}
										else {
											if (getIntent().getStringExtra("part").equals("3")) {
												if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
													_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
													msg3.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
												}
												else {
													msg3.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
												}
											}
											else {
												if (getIntent().getStringExtra("part").equals("4")) {
													if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
														_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
														msg4.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
													}
													else {
														msg4.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
													}
												}
												else {
													if (getIntent().getStringExtra("part").equals("5")) {
														if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
															_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
															msg5.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
														}
														else {
															msg5.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
														}
													}
													else {
														if (getIntent().getStringExtra("part").equals("6")) {
															if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
																_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
																msg6.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
															}
															else {
																msg6.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
															}
														}
														else {
															if (getIntent().getStringExtra("part").equals("7")) {
																if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
																	_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
																	msg7.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																}
																else {
																	msg7.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																}
															}
															else {
																if (getIntent().getStringExtra("part").equals("8")) {
																	if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
																		_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
																		msg8.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																	}
																	else {
																		msg8.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																	}
																}
																else {
																	if (getIntent().getStringExtra("part").equals("9")) {
																		if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
																			_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
																			msg9.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																		}
																		else {
																			msg9.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																		}
																	}
																	else {
																		if (getIntent().getStringExtra("part").equals("10")) {
																			if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
																				_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
																				msg10.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																			}
																			else {
																				msg10.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																			}
																		}
																		else {
																			if (getIntent().getStringExtra("part").equals("11")) {
																				if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
																					_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
																					msg11.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																				}
																				else {
																					msg11.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																				}
																			}
																			else {
																				
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							});
							d.create().show();
						}
						else {
							if (login.getString("name", "").equals(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("name").toString()))) {
								_light();
								d.setTitle("ISNAD");
								d.setIcon(R.drawable.logo);
								d.setMessage("حدد ما تريد تنفيذه :");
								if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("msg").toString()).length() > 0) {
									d.setPositiveButton("نسخ الرسالة", new DialogInterface.OnClickListener() {
										@Override
										public void onClick(DialogInterface _dialog, int _which) {
											((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("msg").toString())));
											SketchwareUtil.showMessage(getApplicationContext(), "تم نسخ الرسالة");
										}
									});
								}
								d.setNeutralButton("حذف الرسالة", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface _dialog, int _which) {
										if (getIntent().getStringExtra("part").equals("1")) {
											if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
												_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
												msg.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
											}
											else {
												msg.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
											}
										}
										else {
											if (getIntent().getStringExtra("part").equals("2")) {
												if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
													_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
													msg2.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
												}
												else {
													msg2.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
												}
											}
											else {
												if (getIntent().getStringExtra("part").equals("3")) {
													if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
														_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
														msg3.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
													}
													else {
														msg3.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
													}
												}
												else {
													if (getIntent().getStringExtra("part").equals("4")) {
														if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
															_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
															msg4.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
														}
														else {
															msg4.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
														}
													}
													else {
														if (getIntent().getStringExtra("part").equals("5")) {
															if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
																_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
																msg5.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
															}
															else {
																msg5.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
															}
														}
														else {
															if (getIntent().getStringExtra("part").equals("6")) {
																if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
																	_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
																	msg6.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																}
																else {
																	msg6.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																}
															}
															else {
																if (getIntent().getStringExtra("part").equals("7")) {
																	if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
																		_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
																		msg7.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																	}
																	else {
																		msg7.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																	}
																}
																else {
																	if (getIntent().getStringExtra("part").equals("8")) {
																		if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
																			_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
																			msg8.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																		}
																		else {
																			msg8.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																		}
																	}
																	else {
																		if (getIntent().getStringExtra("part").equals("9")) {
																			if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
																				_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
																				msg9.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																			}
																			else {
																				msg9.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																			}
																		}
																		else {
																			if (getIntent().getStringExtra("part").equals("10")) {
																				if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
																					_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
																					msg10.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																				}
																				else {
																					msg10.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																				}
																			}
																			else {
																				if (getIntent().getStringExtra("part").equals("11")) {
																					if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()).length() > 0) {
																						_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).delete().addOnSuccessListener(_pic_delete_success_listener).addOnFailureListener(_pic_failure_listener);
																						msg11.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																					}
																					else {
																						msg11.child("m".concat(lm.get((int)_position).get("del").toString())).removeValue();
																					}
																				}
																				else {
																					
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								});
								d.create().show();
							}
							else {
								if (AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("msg").toString()).length() > 0) {
									_light();
									d.setTitle("ISNAD");
									d.setIcon(R.drawable.logo);
									d.setMessage("حدد ما تريد تنفيذه :");
									d.setPositiveButton("نسخ الرسالة", new DialogInterface.OnClickListener() {
										@Override
										public void onClick(DialogInterface _dialog, int _which) {
											((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("msg").toString())));
											SketchwareUtil.showMessage(getApplicationContext(), "تم نسخ الرسالة");
										}
									});
									d.create().show();
								}
							}
						}
					}
				});
				pic2.setOnLongClickListener(new View.OnLongClickListener() {
					@Override
					public boolean onLongClick(View _view) {
						if (!FileUtil.isExistFile(FileUtil.getExternalStorageDir().concat("/Download/"))) {
							FileUtil.makeDir(FileUtil.getExternalStorageDir().concat("/Download/"));
						}
						randomv = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
						uotrandom = "";
						for (int i = 0; i < 8; i++) {
							uotrandom = uotrandom + randomv.charAt(new java.util.Random().nextInt(randomv.length()));
						}
						_firebase_storage.getReferenceFromUrl(AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString())).getFile(new File(FileUtil.getExternalStorageDir().concat("/Download/".concat(uotrandom.concat(".png"))))).addOnSuccessListener(_pic_download_success_listener).addOnFailureListener(_pic_failure_listener).addOnProgressListener(_pic_download_progress_listener);
						SketchwareUtil.showMessage(getApplicationContext(), "جارى تحميل الصورة...");
						return true;
					}
				});
				pic1.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						i.setClass(getApplicationContext(), ImgViewActivity.class);
						i.putExtra("pic", AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()));
						startActivity(i);
					}
				});
				pic2.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						i.setClass(getApplicationContext(), ImgViewActivity.class);
						i.putExtra("pic", AESCrypt.decrypt("r9SWlxXf3kchvHJUvZs1Tmd6jVfSQ5vUNOBJ2e/cvsM=", lm.get((int)_position).get("pic").toString()));
						startActivity(i);
					}
				});
				  } catch (java.security.GeneralSecurityException e){
				      showMessage("password incorrect !" + "\n" + e.toString());
				  }
			
			return _view;
		}
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