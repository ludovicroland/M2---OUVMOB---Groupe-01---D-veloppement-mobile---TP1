package fr.efrei.tp1;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import fr.efrei.tp1.bo.User;
import fr.efrei.tp1.repository.UserRepository;

final public class MainActivity
    extends AppCompatActivity
    implements OnClickListener
{

  //The tag used into this screen for the logs
  public static final String TAG = MainActivity.class.getSimpleName();

  private EditText name;

  private EditText phoneNumer;

  private EditText address;

  private EditText about;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);

    //We first set up the layout linked to the activity
    setContentView(R.layout.activity_main);

    //Then we retrieved the widget we will need to manipulate into the
    name = findViewById(R.id.name);
    phoneNumer = findViewById(R.id.phoneNumber);
    address = findViewById(R.id.address);
    about = findViewById(R.id.about);

    //We configure the click on the save button
    findViewById(R.id.save).setOnClickListener(this);
  }

  @Override
  public void onClick(View v)
  {
    //we first retrieve user's entries
    final String userName = name.getEditableText().toString();
    final String userPhoneNumber = phoneNumer.getEditableText().toString();
    final String userAddress = address.getEditableText().toString();
    final String aboutUser = about.getEditableText().toString();

    //We display the properties into the logcat
    displayUserEntries(userName, userPhoneNumber, userAddress, aboutUser);

    //We check if all entries are valid (not null and not empty)
    final boolean canAddUser = checkFormEntries(userName, userPhoneNumber, userAddress, aboutUser);

    if (canAddUser == true)
    {
      //We add the user to the list and we reset the form
      saveUser(userName, userPhoneNumber, userAddress, aboutUser);
      resetForm();
    }
    else
    {
      //we display a log error and a Toast
      Log.w(MainActivity.TAG, "Cannot add the user");
      Toast.makeText(this, R.string.cannot_add_user, Toast.LENGTH_SHORT).show();
    }
  }

  private void resetForm()
  {
    name.setText(null);
    address.setText(null);
    phoneNumer.setText(null);
    about.setText(null);
  }

  private void saveUser(String userName, String userPhoneNumber, String userAddress,
      String aboutUser)
  {
    UserRepository.getInstance().addUser(new User(userName, userPhoneNumber, userAddress, aboutUser));
  }

  private boolean checkFormEntries(String userName, String userPhoneNumber, String userAddress,
      String aboutUser)
  {
    return TextUtils.isEmpty(userName) == false && TextUtils.isEmpty(userPhoneNumber) == false && TextUtils.isEmpty(userAddress) == false && TextUtils.isEmpty(aboutUser) == false;
  }

  private void displayUserEntries(String userName, String userPhoneNumber, String userAddress,
      String aboutUser)
  {
    Log.d(MainActivity.TAG, "name : '" + userName + "'");
    Log.d(MainActivity.TAG, "phone number : '" + userPhoneNumber + "'");
    Log.d(MainActivity.TAG, "address : '" + userAddress + "'");
    Log.d(MainActivity.TAG, "about : '" + aboutUser + "'");
  }

}