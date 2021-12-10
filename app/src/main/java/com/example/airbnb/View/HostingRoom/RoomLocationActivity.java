package com.example.airbnb.View.HostingRoom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.airbnb.R;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RoomLocationActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.country_actv)
    AutoCompleteTextView country_actv;
    @BindView(R.id.province_actv)
    AutoCompleteTextView province_actv;
    @BindView(R.id.district_actv)
    AutoCompleteTextView district_actv;
    @BindView(R.id.commune_actv)
    AutoCompleteTextView commune_actv;
    @BindView(R.id.address_edt)
    TextInputEditText address_edt;


    ArrayAdapter ct_ad;
    ArrayAdapter pv_ad;
    ArrayAdapter dt_ad;
    ArrayAdapter cm_ad;

    String []country_options = {"Chọn Quốc Gia","United States", "Canada", "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua and/or Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo", "Cook Islands", "Costa Rica", "Croatia (Hrvatska)", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecudaor", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland", "France", "France, Metropolitan", "French Guiana", "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard and Mc Donald Islands", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran (Islamic Republic of)", "Iraq", "Ireland", "Israel", "Italy", "Ivory Coast", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea, Democratic People's Republic of", "Korea, Republic of", "Kosovo", "Kuwait", "Kyrgyzstan", "Lao People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libyan Arab Jamahiriya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia, Federated States of", "Moldova, Republic of", "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfork Island", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia South Sandwich Islands", "South Sudan", "Spain", "Sri Lanka", "St. Helena", "St. Pierre and Miquelon", "Sudan", "Suriname", "Svalbarn and Jan Mayen Islands", "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic", "Taiwan", "Tajikistan", "Tanzania, United Republic of", "Thailand", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States minor outlying islands", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City State", "Venezuela", "Vietnam", "Virigan Islands (British)", "Virgin Islands (U.S.)", "Wallis and Futuna Islands", "Western Sahara", "Yemen", "Yugoslavia", "Zaire", "Zambia", "Zimbabwe" };
    String []province_options = {"Chọn Tỉnh/Thành","Bắc Giang", "Bắc Kạn", "Cao Bằng", "Hà Giang", "Lạng Sơn", "Phú Thọ", "Quảng Ninh", "Thái Nguyên", "Tuyên Quang", "Lào Cai", "Yên Bái","Điện Biên", "Hòa Bình", "Lai Châu", "Sơn La", "Bắc Ninh", "Hà Nam", "Hải Dương", "Hưng Yên", "Nam Định", "Ninh Bình", "Thái Bình", "Vĩnh Phúc", "Hà Nội", "Hải Phòng","Hà Tĩnh", "Nghệ An", "Hồ Chí Minh"};
    String []district_options = {"Chọn Quận/Huyện","Quận 1","Quận 2", "Quận 3","Quận 4","Quận 5", "Quận 6","Quận 7","Quận 8", "Quận 9","Quận 10","Quận 11", "Quận 12","Quận Bình Tân", "Quận Bình Thạnh", "Quận Gò Vấp", "Quận Phú Nhuận", "Quận Tân Bình", "Quận Tân Phú", "Quận Thủ Đức", "Huyện Bình Chánh", "Huyện Cần Giờ", "Huyện Củ Chi", "Huyện Hóc Môn", "Huyện Nhà Bè"};
    String []commune_options = { "Chọn Xã/Phường","An Khánh", "An Lợi Đông", "An Phú", "Bình Chiểu", "Bình Thọ", "Bình Trưng Đông", "Bình Trưng Tây", "Cát Lái", "Hiệp Bình Chánh", "Hiệp Bình Phước", "Hiệp Phú", "Linh Chiểu", "Linh Đông", "Linh Tây", "Linh Trung", "Linh Xuân", "Long Bình", "Long Phước", "Long Thạnh Mỹ", "Long Trường", "Phú Hữu", "Phước Bình", "Phước Long A", "Phước Long B", "Tam Bình", "Tam Phú", "Tân Phú", "Tăng Nhơn Phú A", "Tăng Nhơn Phú B", "Thạnh Mỹ Lợi", "Thảo Điền", "Thủ Thiêm", "Trường Thạnh", "Trường Thọ"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_location);
        ButterKnife.bind(this);
        initToolbar();
        initOptions();
    }
    public void Next(View view) {
        Intent intent = new Intent(getApplicationContext(), RoomContactActivity.class);
        startActivity(intent);
    }
    private void initToolbar() {
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
        {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            setTitle("Cho thuê Phòng/Nhà");
        }
    }
    private  void initOptions ()
    {

        ct_ad = new ArrayAdapter(this, R.layout.item_option, country_options);
        country_actv.setText(ct_ad.getItem(0).toString(), true);
        country_actv.setAdapter(ct_ad);

        pv_ad = new ArrayAdapter(this, R.layout.item_option, province_options);
        province_actv.setText(pv_ad.getItem(0).toString(), true);
        province_actv.setAdapter(pv_ad);

        dt_ad = new ArrayAdapter(this, R.layout.item_option, district_options);
        district_actv.setText(dt_ad.getItem(0).toString(), true);
        district_actv.setAdapter(dt_ad);

        cm_ad = new ArrayAdapter(this, R.layout.item_option, commune_options);
        commune_actv.setText(cm_ad.getItem(0).toString(), true);
        commune_actv.setAdapter(cm_ad);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return true;
    }

    public void getLocation(View view) {
        country_actv.setText("Vietnam", true);

        province_actv.setText("Hồ Chí Minh", true);

        district_actv.setText("Thủ Đức", true);

        commune_actv.setText("Linh Trung", true);

        address_edt.setText("19, Khu Phố 6");
    }
}