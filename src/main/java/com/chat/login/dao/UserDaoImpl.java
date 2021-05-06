package com.chat.login.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import org.springframework.stereotype.Repository;

import com.chat.constant.Constant;
import com.chat.login.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Override
	public void loadUserInfo() {
		User user1 =  new User("001", "user001", "001", "/img/avatar/Member001.jpg");
		User user2 = new User("002", "user002", "002", "/img/avatar/Member002.jpg");
		User user3 = new User("003", "user003", "003", "/img/avatar/Member003.jpg");
		
		user1.setFriendList(generateFriendList("001"));
		user2.setFriendList(generateFriendList("002"));
		user3.setFriendList(generateFriendList("003"));
		
		Constant.userMap.put("user001", user1);
		Constant.userMap.put("user002", user2);
		Constant.userMap.put("user003", user3);
		Constant.userMap.put("001", user1);
		Constant.userMap.put("002", user2);
		Constant.userMap.put("003", user3);
		
		
	}
	
	public List<User> generateFriendList(String userId){
		User user1 =  new User("001", "user001", "001", "/img/avatar/Member001.jpg");
		User user2 = new User("002", "user002", "002", "/img/avatar/Member002.jpg");
		User user3 = new User("003", "user003", "003", "/img/avatar/Member003.jpg");
		List<User> userList = new ArrayList<User>();
		userList.add(user1);
		userList.add(user2);
		userList.add(user3);
		Iterator<User> iterator = userList.iterator();
		while (iterator.hasNext()) {
			User user = (User) iterator.next();
			if (userId.equals(user.getUserId())) {
				iterator.remove();
			}
			
		}
		return userList;
	}
	
	@Override
	public User getByUsername(String username) {
		return Constant.userMap.get(username);
	}

	@Override
	public User getUserById(String userId) {
		User result = null;
		Iterator<Entry<String, User>> iterator = Constant.userMap.entrySet().iterator();
		 while (iterator.hasNext()) {
	            Entry<String, User> entry = iterator.next();
	            if (entry.getValue().getUserId().equals(userId)) {
	                result = entry.getValue();
	                break;
	            }
	        }
	        return result;
	}

}
