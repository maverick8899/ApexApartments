import { ViewIcon } from '@chakra-ui/icons';
import {
    Modal,
    ModalOverlay,
    ModalContent,
    ModalHeader,
    ModalFooter,
    ModalBody,
    ModalCloseButton,
    Button,
    useDisclosure,
    IconButton,
    Text,
    Avatar,
} from '@chakra-ui/react';
const Profile = ({ user, children }) => {
    console.log({ user });
    const { isOpen, onOpen, onClose } = useDisclosure();
    //
    return (
        <>
            {children ? (
                <span onClick={onOpen}>{children}</span>
            ) : (
                <IconButton d={{ base: 'flex' }} icon={<ViewIcon />} onClick={onOpen} />
            )}
            <Modal size="lg" onClose={onClose} isOpen={isOpen} isCentered>
                <ModalOverlay />
                <ModalContent h="350px">
                    <ModalHeader
                        fontSize="40px"
                        fontFamily="Work sans"
                        d="flex"
                        justifyContent="center"
                    >
                        {user?.name}
                    </ModalHeader>
                    <ModalCloseButton />
                    <ModalBody
                        display="flex"
                        flexDirection="column"
                        alignItems="center"
                        gap={2}
                        height={'fit-content'}
                    >
                        <Avatar
                            src={user?.picture}
                            style={{
                                width: '100px',
                                height: '100px',
                            }}
                            name={user?.name}
                        />
                        <Text mt={2} fontSize={{ base: '20px', md: '25px' }}>
                            Email: {user ? user?.email : 'email@placeholder.com'}
                        </Text>
                    </ModalBody>
                    <ModalFooter>
                        <Button onClick={onClose}>Close</Button>
                    </ModalFooter>
                </ModalContent>
            </Modal>
        </>
    );
};

export default Profile;
