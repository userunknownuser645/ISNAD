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
import android.widget.ImageView;
import java.util.Timer;
import java.util.TimerTask;
import android.animation.ObjectAnimator;
import android.view.animation.LinearInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import java.io.InputStream;
import android.content.Intent;
import android.net.Uri;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class MainActivity extends AppCompatActivity {
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private double pos = 0;
	
	private ArrayList<HashMap<String, Object>> lm = new ArrayList<>();
	
	private LinearLayout linear2;
	private LinearLayout linear1;
	private LinearLayout linear4;
	private ImageView imageview1;
	private LinearLayout linear3;
	private ImageView imageview2;
	
	private TimerTask t;
	private ObjectAnimator o = new ObjectAnimator();
	private RequestNetwork r;
	private RequestNetwork.RequestListener _r_request_listener;
	private AlertDialog.Builder d;
	private SharedPreferences login;
	private Intent i = new Intent();
	private TimerTask l;
	private DatabaseReference code = _firebase.getReference("code");
	private ChildEventListener _code_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		imageview2 = (ImageView) findViewById(R.id.imageview2);
		r = new RequestNetwork(this);
		d = new AlertDialog.Builder(this);
		login = getSharedPreferences("login", Activity.MODE_PRIVATE);
		
		_r_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				o.setTarget(imageview2);
				o.setPropertyName("alpha");
				o.setFloatValues((float)(1), (float)(0));
				o.setDuration((int)(1000));
				o.start();
				t = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								o.setTarget(imageview1);
								o.setPropertyName("alpha");
								o.setFloatValues((float)(1), (float)(0));
								o.setDuration((int)(1000));
								o.start();
								t = new TimerTask() {
									@Override
									public void run() {
										runOnUiThread(new Runnable() {
											@Override
											public void run() {
												if (login.getString("login", "").length() > 0) {
													pos = 0;
													code.addListenerForSingleValueEvent(new ValueEventListener() {
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
															if (Double.parseDouble(lm.get((int)0).get("update").toString()) > 0) {
																i.setClass(getApplicationContext(), UpdateActivity.class);
																startActivity(i);
																finish();
															}
															else {
																t = new TimerTask() {
																	@Override
																	public void run() {
																		runOnUiThread(new Runnable() {
																			@Override
																			public void run() {
																				if (lm.get((int)pos).get("code").toString().equals(login.getString("login", ""))) {
																					t.cancel();
																					login.edit().putString("name", lm.get((int)pos).get("name").toString()).commit();
																					login.edit().putString("img", lm.get((int)pos).get("img").toString()).commit();
																					i.setClass(getApplicationContext(), PartsActivity.class);
																					startActivity(i);
																					finish();
																				}
																				else {
																					pos++;
																				}
																			}
																		});
																	}
																};
																_timer.scheduleAtFixedRate(t, (int)(0), (int)(10));
															}
														}
														@Override
														public void onCancelled(DatabaseError _databaseError) {
														}
													});
												}
												else {
													i.setClass(getApplicationContext(), LoginActivity.class);
													startActivity(i);
													finish();
												}
											}
										});
									}
								};
								_timer.schedule(t, (int)(1000));
							}
						});
					}
				};
				_timer.schedule(t, (int)(1000));
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				o.setTarget(imageview2);
				o.setPropertyName("alpha");
				o.setFloatValues((float)(1), (float)(0));
				o.setDuration((int)(1000));
				o.start();
				t = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								o.setTarget(imageview1);
								o.setPropertyName("alpha");
								o.setFloatValues((float)(1), (float)(0));
								o.setDuration((int)(1000));
								o.start();
								t = new TimerTask() {
									@Override
									public void run() {
										runOnUiThread(new Runnable() {
											@Override
											public void run() {
												_light();
												d.setTitle("ISNAD");
												d.setIcon(R.drawable.logo);
												d.setMessage("لا يتوفر إتصال بالإنترنت 🌎 ؟");
												d.setPositiveButton("إعادة المحاولة 🔄", new DialogInterface.OnClickListener() {
													@Override
													public void onClick(DialogInterface _dialog, int _which) {
														o.setTarget(imageview1);
														o.setPropertyName("alpha");
														o.setFloatValues((float)(0), (float)(1));
														o.setDuration((int)(1000));
														o.start();
														t = new TimerTask() {
															@Override
															public void run() {
																runOnUiThread(new Runnable() {
																	@Override
																	public void run() {
																		o.setTarget(imageview2);
																		o.setPropertyName("alpha");
																		o.setFloatValues((float)(0), (float)(1));
																		o.setDuration((int)(1000));
																		o.start();
																		t = new TimerTask() {
																			@Override
																			public void run() {
																				runOnUiThread(new Runnable() {
																					@Override
																					public void run() {
																						r.startRequestNetwork(RequestNetworkController.GET, "https://google.com", "r", _r_request_listener);
																					}
																				});
																			}
																		};
																		_timer.schedule(t, (int)(1000));
																	}
																});
															}
														};
														_timer.schedule(t, (int)(1000));
													}
												});
												d.setNeutralButton("خروج ⬅️", new DialogInterface.OnClickListener() {
													@Override
													public void onClick(DialogInterface _dialog, int _which) {
														finishAffinity();
													}
												});
												d.create().show();
											}
										});
									}
								};
								_timer.schedule(t, (int)(1000));
							}
						});
					}
				};
				_timer.schedule(t, (int)(1000));
			}
		};
		
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
	}
	
	private void initializeLogic() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { Window w = getWindow();  w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS); };
		imageview1.setAlpha((float)(0));
		imageview2.setAlpha((float)(0));
		l = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						imageview2.setRotation((float)(imageview2.getRotation() + -5));
					}
				});
			}
		};
		_timer.scheduleAtFixedRate(l, (int)(0), (int)(10));
		t = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						o.setTarget(imageview1);
						o.setPropertyName("alpha");
						o.setFloatValues((float)(0), (float)(1));
						o.setDuration((int)(1000));
						o.start();
						t = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										o.setTarget(imageview2);
										o.setPropertyName("alpha");
										o.setFloatValues((float)(0), (float)(1));
										o.setDuration((int)(1000));
										o.start();
										t = new TimerTask() {
											@Override
											public void run() {
												runOnUiThread(new Runnable() {
													@Override
													public void run() {
														r.startRequestNetwork(RequestNetworkController.GET, "https://google.com", "r", _r_request_listener);
													}
												});
											}
										};
										_timer.schedule(t, (int)(1000));
									}
								});
							}
						};
						_timer.schedule(t, (int)(1000));
					}
				});
			}
		};
		_timer.schedule(t, (int)(1000));
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
	public void _light () {
		d = new AlertDialog.Builder(this,AlertDialog.THEME_DEVICE_DEFAULT_DARK);
		d.setCancelable(false);
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