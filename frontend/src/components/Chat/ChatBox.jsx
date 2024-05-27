import { Box } from '@chakra-ui/layout';
import store from '../../store';
import SingleChat from './SingleChat';
// import './styles.css';
// import SingleChat from './SingleChat';

const ChatBox = ({ fetchAgain, setFetchAgain }) => {
    const { selectedChat } = store((state) => state.data);

    return (
        <Box
            display={{ base: selectedChat ? 'flex' : 'none', md: 'flex' }}
            alignItems="center"
            flexDir="column"
            p={3}
            bg="white"
            w={{ base: '100%', md: '68%' }}
            borderRadius="lg"
            borderWidth="1px"
            backgroundColor={'rgba(255, 255, 255, 0.8)'}
        >
            <SingleChat fetchAgain={fetchAgain} setFetchAgain={setFetchAgain} />
        </Box>
    );
};

export default ChatBox;
