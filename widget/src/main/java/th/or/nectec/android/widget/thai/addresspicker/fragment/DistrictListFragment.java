/*
 * Copyright 2015 NECTEC
 * National Electronics and Computer Technology Center, Thailand
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package th.or.nectec.android.widget.thai.addresspicker.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import th.or.nectec.android.widget.thai.R;
import th.or.nectec.android.widget.thai.addresspicker.adapter.DistrictAdapter;
import th.or.nectec.android.widget.thai.addresspicker.repository.JsonDistrictRepository;
import th.or.nectec.android.widget.thai.addresspicker.repository.JsonProvinceRepository;
import th.or.nectec.domain.thai.address.district.DistrictChooser;
import th.or.nectec.domain.thai.address.district.DistrictPresenter;
import th.or.nectec.entity.thai.District;
import th.or.nectec.entity.thai.Province;


public class DistrictListFragment extends Fragment {

    public static final String FRAGMENT_TAG = "district_list";

    private static final String PROVINCE_CODE = "province_code";
    private ListView listView;
    private TextView addressInfo;
    private DistrictAdapter provinceAdapter;

    private DistrictChooser districtChooser;
    private DistrictPresenter districtPresenter = new DistrictPresenter() {
        @Override
        public void showDistrictList(List<District> districts) {
            provinceAdapter = new DistrictAdapter(getActivity(), districts);
        }

        @Override
        public void showNotFoundDistrict() {
            Toast.makeText(getActivity(), "ไม่พบอำเภอ", Toast.LENGTH_LONG).show();
        }
    };

    public DistrictListFragment() {
        // Required empty public constructor
    }

    public static DistrictListFragment newInstance(String provinceCode) {
        DistrictListFragment fragment = new DistrictListFragment();
        Bundle args = new Bundle();
        args.putString(PROVINCE_CODE, provinceCode);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_address_list_picker, container, false);
        initInstances(view);

        setupStageHeader();
        return view;
    }

    private void initInstances(View view) {
        listView = (ListView) view.findViewById(R.id.picker_list);
        addressInfo = (TextView) view.findViewById(R.id.address_info);
        districtChooser = new DistrictChooser(new JsonDistrictRepository(getActivity()), districtPresenter);
        districtChooser.showDistrictListByProvinceCode(getProvinceCode());
        listView.setAdapter(provinceAdapter);
    }

    private void setupStageHeader() {
        Province province = new JsonProvinceRepository(getActivity()).findByProvinceCode(getProvinceCode());
        addressInfo.setText(String.format(getString(R.string.breadcrumb_text), province.getRegion().toString(), province.getName()));
    }

    private String getProvinceCode() {
        return getArguments().getString(PROVINCE_CODE);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public District getData() {
        return listView.getCheckedItemPosition() == -1 ? null : provinceAdapter.getItem(listView.getCheckedItemPosition());
    }
}
