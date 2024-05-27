import { CloseIcon } from '@chakra-ui/icons';
import { Badge } from '@chakra-ui/layout';

const UserBadgeItem = ({ users, handleFunction, admin }) => {
    return users.map((user) => (
        <Badge
            key={user._id}
            px={2}
            py={1}
            borderRadius="lg"
            m={1}
            mb={2}
            variant="solid"
            fontSize={12}
            colorScheme="purple"
            cursor="pointer"
        >
            {user.name}
            {admin._id === user._id && <span style={{ color: 'lightgreen' }}> (Admin)</span>}
            {admin._id !== user._id && <CloseIcon pl={1} onClick={() => handleFunction(user)} />}
        </Badge>
    ));
};

export default UserBadgeItem;
