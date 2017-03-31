# UpDownAnimationMenu

<!-- Baidu Button BEGIN -->

<div id="article_content" class="article_content">

<p><br>
</p>
<p><span style="color:rgb(51,51,51); font-family:Arial"><span style="font-size:14px"><strong>前言</strong>：</span><span style="font-family:宋体; text-align:center; background-color:rgb(249,249,249)"><span style="font-size:14px">开源项目BottomBar,实现Android底部菜单(常用菜单，BottomBar实现动画(上下式)&#43;消息菜单，</span></span></span></p>
<p><span style="color:rgb(51,51,51); font-family:Arial"><span style="font-family:宋体; text-align:center; background-color:rgb(249,249,249)"><span style="font-size:14px">BottomBar&#43;ViewPager&#43;Fragment实现炫酷的底部导航效果)</span></span></span><br>
</p>
<p><span style="color:rgb(51,51,51); font-family:Arial"><span style="font-family:宋体; text-align:center; background-color:rgb(249,249,249)"><span style="font-size:14px"><br>
</span></span></span></p>
<p><span style="color:rgb(51,51,51); font-family:Arial"><span style="font-family:宋体; text-align:center; background-color:rgb(249,249,249)"><span style="font-size:14px"><strong>效果：</strong></span></span></span></p>
<p><span style="color:rgb(51,51,51); font-family:Arial"><span style="font-family:宋体; text-align:center; background-color:rgb(249,249,249)"><span style="font-size:14px"><strong><br>
</strong></span></span></span></p>
<p style="text-align:left"><span style="color:rgb(51,51,51); font-family:Arial"><span style="font-family:宋体; text-align:center; background-color:rgb(249,249,249)"><span style="font-size:14px"><strong>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;<img src="http://images2015.cnblogs.com/blog/1130112/201703/1130112-20170331212642180-233844519.gif" alt=""><br>
</strong></span></span></span></p>
<p style="text-align:left"><span style="color:rgb(51,51,51); font-family:Arial"><span style="font-family:宋体; text-align:center; background-color:rgb(249,249,249)"><span style="font-size:14px"><strong><br>
</strong></span></span></span></p>
<p style="text-align:left"><span style="color:rgb(51,51,51); font-family:Arial"><span style="font-family:宋体; text-align:center; background-color:rgb(249,249,249)"><span style="font-size:14px"><strong><br>
</strong></span></span></span></p>
<p style="text-align:left"><span style="color:rgb(51,51,51); font-family:Arial"><span style="font-family:宋体; text-align:center; background-color:rgb(249,249,249)"><span style="font-size:14px"><strong><span style="color:rgb(51,51,51); font-family:&quot;Microsoft YaHei&quot;,Arial; font-size:14px">开发环境：AndroidStudio2.2.1&#43;gradle-2.14.1</span><br>
</strong></span></span></span></p>
<p style="text-align:left"><span style="color:rgb(51,51,51); font-family:Arial"><span style="font-family:宋体; text-align:center; background-color:rgb(249,249,249)"><span style="font-size:14px"><strong><span style="color:rgb(51,51,51); font-family:&quot;Microsoft YaHei&quot;,Arial; font-size:14px"><br>
</span></strong></span></span></span></p>
<p style="text-align:left"><span style="color:rgb(51,51,51); font-family:Arial"><span style="font-family:宋体; text-align:center; background-color:rgb(249,249,249)"><span style="font-size:14px"><strong><span style="color:rgb(51,51,51); font-family:&quot;Microsoft YaHei&quot;,Arial; font-size:14px">引入依赖:</span></strong></span></span></span></p>
<p><br>
</p>
<p><pre name="code" class="java"><span style="font-size:18px;">  compile 'com.android.support:appcompat-v7:23.0.0'
  compile 'com.android.support:design:23.+'
  compile 'com.roughike:bottom-bar:1.3.+'</span>
</pre><br>
<br>
</p>
<p style="text-align:left"><span><span><span><span style="text-align:center; background-color:rgb(249,249,249)"><strong><span style="color:rgb(51,51,51); font-family:&quot;Microsoft YaHei&quot;,Arial; font-size:14px"><span style="font-family:SimHei">部分代码：</span></span></strong></span></span></span></span></p>
<p style="text-align:left"><br>
</p>
<p style="text-align:left"><pre name="code" class="java">public class Menu1Activity extends AppCompatActivity {

    private BottomBar mBottomBar;
    private MainFragment mMainFragment;
    private TwoFragment twoFragment;
    private ThreeFragment threeFragment;
    private FourFragment fourFragment;
    private FragmentTransaction transaction;
    private BottomBarBadge unreadMessages;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mBottomBar = BottomBar.attach(this, savedInstanceState);
        // 千万注意：这个方法要用在 setItemsFromMenu 之前，也就是tab还没有设置之前要先调用，不然会报错。
        mBottomBar.noTabletGoodness();

        //添加初始Fragment
        defaultFragment(null == mMainFragment ? mMainFragment = MainFragment.newInstance() : mMainFragment);
        mBottomBar.setItems(R.menu.bottombar_menu);
        mBottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                //单击事件 menuItemId 是 R.menu.bottombar_menu 中 item 的 id
                switch (menuItemId) {
                    case R.id.bb_menu_main:
                        if (null == mMainFragment) {
                            mMainFragment = MainFragment.newInstance();
                        }
                        replaceFragment(mMainFragment);
                        break;
                    case R.id.bb_menu_chart:
                        if (null == twoFragment) {
                            twoFragment = TwoFragment.newInstance();
                        }
                        replaceFragment(twoFragment);
                        unreadMessages.setVisibility(View.GONE);
                        break;
                    case R.id.bb_menu_timeline:
                        if (null == threeFragment) {
                            threeFragment = ThreeFragment.newInstance();
                        }
                        replaceFragment(threeFragment);
                        break;
                    case R.id.bb_menu_about:
                        if (null == fourFragment) {
                            fourFragment = FourFragment.newInstance();
                        }
                        replaceFragment(fourFragment);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                //重选事件，当前已经选择了这个，又点了这个tab。微博点击首页刷新页面
            }
        });
        // 当点击不同按钮的时候，设置不同的颜色
        // 可以用以下三种方式来设置颜色.
        mBottomBar.mapColorForTab(0, ContextCompat.getColor(this, R.color.colorPrimary));
        mBottomBar.mapColorForTab(1, ContextCompat.getColor(this, R.color.colorAccent));
        mBottomBar.mapColorForTab(2, ContextCompat.getColor(this, R.color.green));
        mBottomBar.mapColorForTab(3, ContextCompat.getColor(this, R.color.orange));
        setMsg();
    }
    

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //保存BottomBar的状态
        mBottomBar.onSaveInstanceState(outState);
    }

  
  
  private void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();
        transaction.replace(R.id.main_fragment_layout, fragment);
//        transaction.hide();
        transaction.commit();

    }



  private void defaultFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();
        transaction.add(R.id.main_fragment_layout, fragment);
        transaction.commit();
   }

    private void setMsg() {
        // 为tab设置一个标签，“信息”提示的数字
        // 参数分别是：第几个tab；小圆圈的颜色；显示的数字
        unreadMessages = mBottomBar.makeBadgeForTabAt(1, &quot;#FF0000&quot;, 13);

        // 设置显示或隐藏
        unreadMessages.show();
  //        unreadMessages.hide();
        // 设置显示的数字
        unreadMessages.setCount(4);

        // 设置显示/消失动画的延迟时间
        unreadMessages.setAnimationDuration(200);

        // 如果不点它，它一直显示
        unreadMessages.setAutoShowAfterUnSelection(true);
    }</pre><br>
<br>
</p>
  
</div>



<!-- Baidu Button END -->
