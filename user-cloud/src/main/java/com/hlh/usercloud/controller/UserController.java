package com.hlh.usercloud.controller;
import cn.authing.core.graphql.GraphQLException;
import cn.authing.core.types.*;
import com.hlh.usercloud.common.ResponseResult;
import com.hlh.usercloud.common.ResultCode;
import com.hlh.usercloud.config.AuthenticationConfig;
import com.hlh.usercloud.config.MangerConfig;
import com.hlh.usercloud.domain.dto.*;
import com.hlh.usercloud.domain.dto.vo.EmailVo;
import com.hlh.usercloud.domain.dto.vo.PasswordVo;
import com.hlh.usercloud.domain.dto.vo.UserDetail;
import com.hlh.usercloud.domain.dto.vo.UserList;
import com.hlh.usercloud.utils.JwtOperator;
import com.hlh.usercloud.utils.MinIoTemplate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author hlh
 */
@Api(tags = "用户模块")
@RestController
@Slf4j
@RequestMapping(value = "/users")
public class UserController {
    @Resource
    AuthenticationConfig authenticationConfig;
    @Resource
    MangerConfig mangerConfig;
    @Resource
    private MinIoTemplate minIoTemplate;
//    @Resource
//    private RedisTemplate redisTemplate;
    @Resource
    private JwtOperator jwtOperator;
    /**
     * 手机密码登录
     */
    @ApiImplicitParam(name = "login1Dto",value = "登录对象",required = true)
    @ApiOperation(value = "手机密码登录")
    @PostMapping("/phone")
    public ResponseResult phoneAndPasswordLogin(@RequestBody Login1Dto login1Dto) throws IOException, GraphQLException {
//        ValueOperations ops = redisTemplate.opsForValue();
        User user = authenticationConfig.getClientInstance().loginByPhonePassword(new LoginByPhonePasswordInput(login1Dto.getPhone(), login1Dto.getPassword())).execute();
//        ops.set(user.getId(), Objects.requireNonNull(user.getToken()));
        UserVo userVo = UserVo.builder().id(user.getId()).token(user.getToken()).build();
        return ResponseResult.success(userVo);
    }

    /**
     * 手机验证码登录
     */
    @ApiImplicitParam(name = "phoneLoginDto",value = "登录对象",required = true)
    @ApiOperation(value = "手机验证码登录")
    @PostMapping("/login")
    public ResponseResult phoneAndVertifyLogin(@RequestBody PhoneLoginDto phoneLoginDto) throws IOException, GraphQLException {
//        ValueOperations ops = redisTemplate.opsForValue();
        User user = authenticationConfig.getClientInstance().loginByPhoneCode(new LoginByPhoneCodeInput(phoneLoginDto.getPhone(), phoneLoginDto.getCode())).execute();
//        ops.set(user.getId(), Objects.requireNonNull(user.getToken()));
        UserVo userVo = UserVo.builder().id(user.getId()).token(user.getToken()).build();
        return ResponseResult.success(userVo);
    }

    /**
     * 获取当前用户信息
     */
    @ApiImplicitParam()
    @ApiOperation(value = "获取当前用户信息")
    @GetMapping("/getCurUsr")
    public ResponseResult phoneAndPassword() throws IOException, GraphQLException {
        User user = authenticationConfig.getClientInstance().getCurrentUser().execute();
        return ResponseResult.success(user);
    }

    /**
     * 用户名密码注册
     */
    @ApiImplicitParam(name = "registerDto",value = "注册对象",required = true)
    @ApiOperation(value = "用户名密码注册")
    @PostMapping("/register")
    public ResponseResult usernameAndPasswordRegister(@RequestBody RegisterDto registerDto) throws IOException, GraphQLException {
        User user = authenticationConfig.getClientInstance().registerByUsername(new RegisterByUsernameInput(registerDto.getUsername(), registerDto.getPassword())).execute();
        UserVo userVo = UserVo.builder().id(user.getId()).token(user.getToken()).build();
        return ResponseResult.success(userVo);
    }

    /**
     * 手机验证码注册
     */
    @ApiImplicitParam(name = "phoneRegisterDto",value = "注册对象",required = true)
    @ApiOperation(value = "手机验证码注册")
    @PostMapping("/phoneRegister")
    public ResponseResult phoneRegister(@RequestBody PhoneRegisterDto phoneRegisterDto) throws IOException, GraphQLException {
        RegisterByPhoneCodeInput param = new RegisterByPhoneCodeInput(phoneRegisterDto.getPhone(), phoneRegisterDto.getCode()).withPassword(phoneRegisterDto.getPassword());
        User user = authenticationConfig.getClientInstance().registerByPhoneCode(param).execute();
        UserVo userVo = UserVo.builder().id(user.getId()).token(user.getToken()).build();
        return ResponseResult.success(userVo);
    }

    /**
     * 发送验证码
     */
    @ApiImplicitParam(name = "phoneDto",value = "验证码对象",required = true)
    @ApiOperation(value = "发送验证码")
    @PostMapping("/sendSms")
    public ResponseResult sendSms(@RequestBody PhoneDto phoneDto) throws IOException {
        CommonMessage execute = authenticationConfig.getClientInstance().sendSmsCode(phoneDto.getPhone()).execute();
        return ResponseResult.success(execute);
    }

    /**
     * 获取用户信息
     */
//    @ApiImplicitParam(name = "id",value = "用户ID",required = true)
//    @ApiOperation(value = "获取用户信息")
//    @GetMapping("/getUser/{id}")
//    public ResponseResult getUser(@PathVariable String id,@RequestHeader(value = "token", required = true) String token) throws IOException, GraphQLException {
//        ValueOperations ops = redisTemplate.opsForValue();
//        Boolean aBoolean = jwtOperator.validateToken(token);
//        Object o = ops.get(id);
//        assert o != null;
//        if (aBoolean){
//            if (o.toString().equals(token)){
//                User user = mangerConfig.getClientInstance1().users().detail(id,true,true).execute();
//                PaginatedRoles roles = mangerConfig.getClientInstance1().users().listRoles(user.getId()).execute();
//                List<SearchUserRoleOptInput> list1 = new ArrayList<>();
//                PaginatedUsers result = mangerConfig.getClientInstance1().users().search(new SearchUserParam("").withRoleOpts(list1)).execute();
//                List<Role> role = roles.getList();
//                List<String> list = new ArrayList<>();
//                for (Role role1 : role) {
//                    list.add(role1.getCode());
//                }
//                List<UserCustomData> customData = user.getCustomData();
//                assert customData != null;
//                UserDto userDto = UserDto.builder().id(user.getId()).sno(customData.get(0).getValue()).classes(customData.get(1).getValue()).college(customData.get(2).getValue()).phone(user.getPhone()).photo(user.getPhoto()).name(user.getName()).roleList(list).gender(user.getGender()).email(user.getEmail())
//                        .address(user.getAddress()).teacherName("陶然然").teacherPhone("12345678").build();
//                return ResponseResult.success(userDto);
//            }else{
//                return ResponseResult.failure(ResultCode.USER_NOT_SIGN_IN);
//            }
//        }else{
//            return ResponseResult.failure(ResultCode.USER_TOKEN_FAIL);
//        }
//    }

    /**
     * 根据电话号码查询用户
     */
    @GetMapping("/phone/{phone}")
    public ResponseResult getUserByPhone(@PathVariable String phone) throws IOException, GraphQLException {
        PaginatedUsers result = mangerConfig.getClientInstance1().users().search(phone).execute();
        // 当前页用户列表
        List<User> list = result.getList();
        String id = list.get(0).getId();
        return ResponseResult.success(id);
    }

    @GetMapping("/getUserNoToken/{id}")
    public ResponseResult getUserno(@PathVariable String id) throws IOException, GraphQLException {
                User user = mangerConfig.getClientInstance1().users().detail(id,true,true).execute();
                PaginatedRoles roles = mangerConfig.getClientInstance1().users().listRoles(user.getId()).execute();
                List<SearchUserRoleOptInput> list1 = new ArrayList<>();
                List<Role> role = roles.getList();
                List<String> list = new ArrayList<>();
                for (Role role1 : role) {
                    list.add(role1.getCode());
                }
                List<UserCustomData> customData = user.getCustomData();
                assert customData != null;
                UserDto userDto = UserDto.builder().id(user.getId()).sno(customData.get(0).getValue()).classes(customData.get(1).getValue()).college(customData.get(2).getValue()).phone(user.getPhone()).photo(user.getPhoto()).name(user.getName()).roleList(list).gender(user.getGender()).email(user.getEmail())
                        .address(user.getAddress()).teacherName("陶然然").teacherPhone("12345678").teacherId("6369b69fbc057197cffcd4eb").fuDaoYuanId("6375c47bc0eae1ec3f2e2ab5").yuanZhangId("638d945dc611f6d6088b4f79").build();
        return ResponseResult.success(userDto);
    }
    /**
     * 绑定手机号
     */
    @ApiImplicitParam(name = "phoneLoginDto",value = "绑定对象",required = true)
    @ApiOperation(value = "绑定手机号")
    @PostMapping("/bindPhone")
    public ResponseResult bindPhone(@RequestBody PhoneLoginDto phoneLoginDto) throws IOException, GraphQLException {
        User user = authenticationConfig.getClientInstance().bindPhone(phoneLoginDto.getPhone(), phoneLoginDto.getCode()).execute();
        return ResponseResult.success(user);
    }

    /**
     * 修改密码
     */
    @ApiImplicitParam(name = "passwordVo",value = "密码对象",required = true)
    @ApiOperation(value = "修改密码")
    @PostMapping("/changePwd")
    public ResponseResult changePwd(@RequestBody PasswordVo passwordVo) throws IOException, GraphQLException {
        User user = authenticationConfig.getClientInstance().updatePassword(passwordVo.getNewPwd(), passwordVo.getOldPwd()).execute();
        return ResponseResult.success(user);
    }

    /**
     * 上传头像
     */
    @ApiImplicitParam(name = "photo",value = "头像文件",required = true)
    @ApiOperation(value = "上传头像")
    @PostMapping("/upload")
    public ResponseResult upload(MultipartFile photo) throws Exception {
        minIoTemplate.makeBucket("hlh");
        String originalFileName = photo.getOriginalFilename();
        minIoTemplate.putObject("hlh",originalFileName,photo.getInputStream());
        return ResponseResult.success("http://124.221.232.15:9090" + "/" + "hlh" + "/" + originalFileName);
    }

    /**
     * 修改用户资料
     */
    @ApiImplicitParam(name = "userDetail",value = "用户资料对象",required = true)
    @ApiOperation(value = "修改用户资料")
    @PostMapping("/edit")
    public ResponseResult edit(@RequestBody UserDetail userDetail) throws IOException {
        User result = mangerConfig.getClientInstance1().users().update(userDetail.getId(), new UpdateUserInput().withName(userDetail.getName()).withGender(userDetail.getGender()).withPhoto(userDetail.getPhoto())).execute();
        return ResponseResult.success(result);
    }

    /**
     * 获取用户列表
     */
    @ApiImplicitParam(name = "page",value = "当前页", required = true)
    @ApiOperation(value = "获取用户列表")
    @GetMapping("/userlist")
    public ResponseResult getUserList(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer limit) throws IOException, GraphQLException {
        PaginatedUsers result = mangerConfig.getClientInstance1().users().list(page, limit).execute();
        // 当前页用户列表
        List<User> list = result.getList();
        List<UserList> list1 = new ArrayList<>();
        for (User user : list) {
            UserList userList = new UserList(user.getId(), user.getPhone(),user.getPhoto(), user.getName());
            list1.add(userList);
        }
        return ResponseResult.success(list1);
    }

    /**
     * 修改手机号
     */
    @ApiImplicitParam(name = "phoneLoginDto",value = "手机号对象",required = true)
    @ApiOperation(value = "修改手机号")
    @PostMapping("/changPhone")
    public ResponseResult changPhone(@RequestBody PhoneLoginDto phoneLoginDto) throws IOException, GraphQLException {
        User user = authenticationConfig.getClientInstance().updatePhone(phoneLoginDto.getPhone(), phoneLoginDto.getCode()).execute();
        return ResponseResult.success(user);
    }

    /**
     * 发送邮件验证邮箱
     */
    @ApiImplicitParam(name = "emailVo",value = "邮件对象",required = true)
    @ApiOperation(value = "发送邮件验证邮箱")
    @PostMapping("/sendEmail")
    public ResponseResult send(@RequestBody EmailVo emailVo) throws IOException, GraphQLException {
        authenticationConfig.getClientInstance().sendEmail(emailVo.getEmail(), EmailScene.VERIFY_EMAIL).execute();
        return ResponseResult.success("发送成功");
    }

    /**
     * 绑定邮箱
     */
    @ApiImplicitParam(name = "emailDto",value = "邮件对象",required = true)
    @ApiOperation(value = "绑定邮箱")
    @PostMapping("/bindEmail")
    public ResponseResult bindEmail(@RequestBody EmailDto emailDto) throws IOException, GraphQLException {
        User user = authenticationConfig.getClientInstance().bindEmail(emailDto.getEmail(), emailDto.getCode()).execute();
        if(user == null){
            return ResponseResult.failure(ResultCode.USER_BIND_EMAIL_FAIL);
        }
        return ResponseResult.success("绑定成功");
    }

    /**
     * 修改邮箱
     */
    @ApiImplicitParam(name = "emailDto",value = "邮件对象",required = true)
    @ApiOperation(value = "修改邮箱")
    @PostMapping("/changeEmail")
    public ResponseResult changeEmail(@RequestBody EmailDto emailDto) throws IOException, GraphQLException {
        User user = authenticationConfig.getClientInstance().updateEmail(emailDto.getEmail(), emailDto.getCode()).execute();
        if(user == null){
            return ResponseResult.failure(ResultCode.USER_BIND_EMAIL_FAIL);
        }
        return ResponseResult.success("修改成功");
    }

    @ApiImplicitParam(name = "phoneRegisterDto",value = "重置密码对象",required = true)
    @ApiOperation(value = "通过手机验证码重置密码")
    @PostMapping("/resetPwd")
    public ResponseResult reset(@RequestBody PhoneRegisterDto phoneRegisterDto) throws IOException, GraphQLException {
        authenticationConfig.getClientInstance().resetPasswordByPhoneCode(phoneRegisterDto.getPhone(), phoneRegisterDto.getCode(), phoneRegisterDto.getPassword()).execute();
        return ResponseResult.success("重置成功");
    }
}
