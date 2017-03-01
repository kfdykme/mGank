#kGank

##介绍

用[gank](gank.io/api)
提供的api制作的app


摸索着使用了MVP模式
##功能

- 提供gank网上的数据
- 点击单项可进入对应页面

##未完成功能

- 下拉刷新
- 收藏页面
- 侧边栏菜单
- 搜索界面
- 暂无

##记录

1. ####listview的简单使用


 ``` Java

					// Creat a list of HashMap to get the datas shows on listview
					ArrayList<HashMap<String,Object>> mArrayList = new ArrayList<HashMap<String, Object>>();
					int mId = 0;
					for(result mResult : fList_result){

						HashMap<String, Object> map = new HashMap<String, Object>();
						map.put("Desc",mResult.getDesc());
						map.put("Date",mResult.getPublishedAt());
						map.put("Who",mResult.getWho());
						map.put("Id",(++mId) +"");
						mArrayList.add(map);
					}

					
					mSimpleAdapter = new SimpleAdapter(
						getContext(),
						mArrayList,
						R.layout.listview_dataitem,
						new String[] {"Desc","Date","Who","Id"},
						new int[] {R.id.listview_dataitem_descText,
							R.id.listview_dataitem_datetext,
							R.id.listview_dataitem_whotext,
							R.id.listview_dataitem_IdText});

					mListView.setAdapter(mSimpleAdapter);

					mListView.setOnItemClickListener(new OnItemClickListener(){

							@Override
							public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4)
							{
								LinearLayout l = (LinearLayout) p2;
								TextView t = (TextView)l.findViewById(R.id.listview_dataitem_descText);

								for(result resultb :fList_result){
									if(resultb.getDesc().equals(t.getText().toString()))
										openWebWindow(resultb.getUrl());
								}


								// TODO: Implement this method
							}
						});
						
``` 
2. sugar的简单使用
3. okhttp3的简单使用
4. 
5. 
6. 
7.

##备注

AIDE 无法使用引入了注解的库
有机会弄明白原因并尝试使用

###### greenDao & retrofit & maybe others need it 









