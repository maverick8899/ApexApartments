//đặt name Chat is counterpart
export const getSender = (loggedUser, users) => {
    // console.log({ loggedUser, users });
    return loggedUser?.name === users[0]?.name ? users[1]?.name : users[0]?.name;
};
//đặt name Chat is counterpart
export const getPicture = (loggedUser, users) => {
    // console.log({ loggedUser, users });
    return loggedUser?.name === users[0]?.name ? users[1]?.picture : users[0]?.picture;
};
export const getSenderFull = (loggedUser, users) => {
    return loggedUser._id === users[0]._id ? users[1] : users[0];
};
//nếu true thì set avatar.
//nếu mess hiện tại không phải isLastMessage
//nếu mess tiếp theo cùng sender mess hiện tại hoặc mess tiếp theo khác mess của người gửi hiện tại thì
// mess của người gửi hiện tại khác người dùng hiện tại -> CHUYỂN SANG MESS NGƯỜI DÙNG MỚI
export const isSameSender = (messages, m, i, userId) => {
    return (
        i < messages.length - 1 &&
        (messages[i + 1]?.sender?._id !== m?.sender?._id ||
            messages[i + 1]?.sender?._id === undefined) &&
        messages[i]?.sender?._id !== userId
    );
};
// khi index mess sender === số lượng mess cùng sender trong chain mess thì
// nếu mess cuối khác
export const isLastMessage = (messages, i, userId) => {
    return (
        i === messages.length - 1 &&
        messages[messages.length - 1]?.sender?._id !== userId &&
        messages[messages.length - 1]?.sender?._id
    );
};

// căn lề user current next to left
export const isSameSenderMargin = (messages, m, i, userId) => {
    // mess hiện tại khác mess user current
    if (
        i < messages.length - 1 &&
        messages[i + 1]?.sender?._id === m?.sender?._id &&
        messages[i]?.sender?._id !== userId
    ) {
        return 33;
    }
    // // mess hiện tại same mess user current và isLastMessage
    else if (
        (i < messages.length - 1 &&
            messages[i + 1]?.sender?._id !== m?.sender?._id &&
            messages[i]?.sender?._id !== userId) ||
        (i === messages.length - 1 && messages[i]?.sender?._id !== userId)
    ) {
        return 0;
    } else {
        return 'auto';
    }
};

// tạo padding cho mess trong chain same sender
export const isSameUser = (messages, m, i) => {
    return i > 0 && messages[i - 1]?.sender?._id === m?.sender?._id;
};
