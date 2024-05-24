import { Avatar } from '@chakra-ui/avatar';
import { Tooltip } from '@chakra-ui/tooltip';
import ScrollableFeed from 'react-scrollable-feed';
import {
    isLastMessage,
    isSameSender,
    isSameSenderMargin,
    isSameUser,
} from '../../services/Chat.service';
import store from '../../store';

const ScrollableChat = ({ messages }) => {
    const { user } = store((state) => state.data);

    return (
        <ScrollableFeed>
            {messages?.map((m, i) => (
                <div style={{ display: 'flex', alignItems: 'center' }} key={m._id + i}>
                    {/* khi khác người gửi nghĩa là isSame=false thì isLast run trả về true để render Avatar */}
                    {(isSameSender(messages, m, i, user._id) ||
                        isLastMessage(messages, i, user._id)) && (
                        <Tooltip label={m.sender.name} placement="bottom-start" hasArrow>
                            <Avatar
                                mt="7px"
                                mr={1}
                                size="sm"
                                cursor="pointer"
                                name={m.sender.name}
                                src={m.sender.picture}
                            />
                        </Tooltip>
                    )}
                    <span
                        style={{
                            backgroundColor: `${
                                m?.sender?._id === user._id ? '#BEE3F8' : '#B9F5D0'
                            }`,
                            marginLeft: isSameSenderMargin(messages, m, i, user._id),
                            marginTop: isSameUser(messages, m, i, user._id) ? 1 : 15,
                            marginBottom: isLastMessage(messages, i, user._id) && 10,
                            borderRadius: '20px',
                            padding: '5px 15px',
                            maxWidth: '75%',
                        }}
                    >
                        {m.content}
                    </span>
                </div>
            ))}
        </ScrollableFeed>
    );
};

export default ScrollableChat;
